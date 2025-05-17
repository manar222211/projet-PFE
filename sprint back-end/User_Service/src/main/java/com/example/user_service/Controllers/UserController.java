package com.example.user_service.Controllers;

import com.example.user_service.DTO.UserDTO;
import com.example.user_service.Entities.Mail;
import com.example.user_service.Entities.RefreshToken;
import com.example.user_service.Entities.UserEntity;
import com.example.user_service.Payload.Request.LoginRequest;
import com.example.user_service.Payload.Response.JwtResponse;
import com.example.user_service.Payload.Response.MessageResponse;
import com.example.user_service.Repositories.UserRepo;
import com.example.user_service.Services.RefreshTokenService;
import com.example.user_service.Services.UserDetailsImpl;
import com.example.user_service.Services.UserService;
import com.example.user_service.jwt.JwtUtils;
import jakarta.ws.rs.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.user_service.utils.StorageService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@RestController
@RequestMapping("/User")
@CrossOrigin(origins = "*")



public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    @Autowired
    StorageService storageService;

@Autowired
    private JavaMailSender javaMailSender;

@Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RefreshTokenService refreshTokenService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest){

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));

        Optional<UserEntity> u=userRepo.findByUsername(loginRequest.getUsername());

        if (u.get().isConfirm()==true){
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetailsImpl userDetails=(UserDetailsImpl) authentication.getPrincipal();

            String jwt=jwtUtils.generateJwtToken(userDetails);

            List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

            return ResponseEntity.ok(
                    new JwtResponse(jwt,
                            "bearer",
                            refreshToken.getToken(),
                            userDetails.getId(),
                            userDetails.getUsername(),
                            userDetails.getEmail(),
                        roles.get(0)
                    )
            )
            ;






        }else {
            throw new RuntimeException("user not confirmed");
        }
    }



    @GetMapping("/signout")
    public ResponseEntity<?> logoutUser(){
    UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Long userId = userDetails.getId();

    refreshTokenService.deleteByUserId(userId);
    return ResponseEntity.ok(new MessageResponse("log out successeful"));
    }


    @GetMapping("/confirm")
    public ResponseEntity<?> confirm(@RequestParam String email){
        UserEntity user= userRepo.findFirstByEmail(email);
        user.setConfirm(true);
        userRepo.save(user);
        return ResponseEntity.ok("is confirm");


    }





    @PostMapping("/createphoto")
    public UserDTO creationphoto(UserEntity userEntity ,@RequestParam("file") MultipartFile file)

            throws MessagingException
{


    String namephoto = storageService.store(file);//recuperation nom photo à partir de postman
    userEntity.setPhoto(namephoto); //attachement de photo


    userEntity.setPassword(encoder.encode(userEntity.getPassword()));//cryptage de mot de passe


    UserEntity  x=userService.create(userEntity);
    UserDTO ustdo=new UserDTO().ToDTO(x);
String to=ustdo.getEmail();
String from="adminrh@gmail.com";
String subject = "creation compte";
String content = "lettre";

    MimeMessage message = javaMailSender.createMimeMessage();
    MimeMessageHelper messageHelper=new MimeMessageHelper(message);
    messageHelper.setFrom(from);
    messageHelper.setTo(to);
    messageHelper.setSubject(subject);
    messageHelper.setText("<html><body>"+content+" <br><a href = http://localhost:8764/User/confirm?email="+ustdo.getEmail()+"> verify</br></body></html>",true);
    javaMailSender.send(message);


    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
    // give the logged-in user's real email
    Long userIdThatCreatesTheCToken= userDetails.getId();
    System.out.println("===============================get email  from spring security of the user id that creates the confirmation token : " + userIdThatCreatesTheCToken);

    System.out.println("===============================get email  from spring security: " + from);


    return ustdo;
}

@PutMapping("/updatepicture/{id}")
public UserDTO updatephoto (@RequestParam("file")MultipartFile file,@PathVariable("id")Long id){
    UserEntity x = userService.details(id);
    String namephoto =storageService.store(file);
    x.setPhoto(namephoto);
    UserEntity u = userService.update(x);
    UserDTO udto=new UserDTO().ToDTO(u);
    return udto;
}



    @GetMapping("/all")
    public List<UserDTO> listtotal() {
List<UserDTO> finallist = new ArrayList<>();
 List<UserEntity> list=userService.All();
 list.forEach(userEntity -> {
     UserDTO myuser=new UserDTO().ToDTO(userEntity);
     myuser.setId(userEntity.getId());
     myuser.setGender(userEntity.getGender());
     finallist.add(myuser);
 });
 return finallist;
    }




    @GetMapping("/allbyrole")
    public List<UserDTO>listrole(@RequestParam String role){
    List<UserDTO> finallist = new ArrayList<>();
    List<UserEntity> list= userService.AllByRole(role);
    list.forEach(userEntity -> {
        UserDTO R=new UserDTO().ToDTO(userEntity);
        R.setId(userEntity.getId());
        finallist.add(R);
    });
    return finallist;
    }









    @GetMapping("/details/{id}")
    public UserDTO showdetails(@PathVariable Long id) {

System.out.println(("*********ID*********"+ id));
        UserEntity u = userService.details(id);
        UserDTO udto = new UserDTO().ToDTO(u);//conversion vers dto
        udto.setId(u.getId());
        if (udto != null) {
            udto.setGender(u.getGender());
            return udto;
        } else {
            System.out.println("not found with this id");
            return null;
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        userService.Delete(id);
    }




    @PutMapping("/update/{id}")

        public UserDTO update( @PathVariable Long id , UserEntity x, @RequestParam(value = "file", required = false) MultipartFile file) {
        x.setId(id);
        UserEntity lastuser = userService.details(id);

        if (file != null && !file.isEmpty())
        {
            String namephoto = storageService.store(file);
            x.setPhoto(namephoto);
        }
        if (x.getUsername() == null) {
            x.setUsername(lastuser.getUsername());

        }
        if (x.getEmail() == null) {
            x.setEmail(lastuser.getEmail());
        }

        if (x.getAdresse() == null) {
            x.setAdresse(lastuser.getAdresse());
        }
        if (x.getPassword() == null) {
            x.setPassword(lastuser.getPassword());
        }

        if (x.getPassword() != null){
            x.setPassword(encoder.encode(x.getPassword()));
        }




        if (x.getFirstname() == null) {
            x.setFirstname(lastuser.getFirstname());
        }

        if (x.getLastname() == null) {
            x.setLastname(lastuser.getLastname());
        }
        if (x.getPhone() == null) {
            x.setPhone(lastuser.getPhone());
        }

UserEntity u= userService.update(x);
        UserDTO udto=new UserDTO().ToDTO(u);
        return udto;

    }


    @CrossOrigin(origins = "http://localhost:4200")

    @PostMapping("/SendMail")
    public ResponseEntity<?> SendMail(@RequestBody Mail mail) {
        try {
            String to = mail.getTo();
            String subject = mail.getSubject();
            String content = mail.getContent();
            String from = mail.getFrom();
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper=new MimeMessageHelper(message);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText("<html><body>"+content+"</body></html>",true);
            javaMailSender.send(message);
            return ResponseEntity.ok("Please check your inbox.");
        } catch (MessagingException e) {
            // Log the error and return a response with details
            logger.error("Error sending email: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email: " + e.getMessage());
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            logger.error("Unexpected error: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }



    private void validateUserData(UserDTO userDTO) {
        if (userDTO.getEmail() == null || userDTO.getEmail().isBlank()) {
            throw new BadRequestException("Email is required.");
        }
        if (!isValidEmail(userDTO.getEmail())) {
            throw new BadRequestException("Invalid email format.");
        }
        if (userDTO.getPassword() == null || userDTO.getPassword().length() < 6) {
            throw new BadRequestException("Password must be at least 6 characters.");
        }
        // Normalize the username
//        String normalizedUsername = UsernameUtils.normalizeUsername(userDTO.getFirstname(), userDTO.getLastname());

        // Check if username already exists in the database
        if (userRepo.existsByUsername(userDTO.getUsername())) {
            throw new BadRequestException("Username already taken, please choose a different one.");
        }

        if (userDTO.getFirstname() == null || userDTO.getFirstname().isBlank()) {
            throw new BadRequestException("Firstname is required.");
        }
        if (userDTO.getLastname() == null || userDTO.getLastname().isBlank()) {
            throw new BadRequestException("Lastname is required.");
        }

        if (userDTO.getPhone() == null || userDTO.getPhone().isBlank()) {
            throw new BadRequestException("Phone number is required.");
        }
        if (!userDTO.getPhone().matches("^\\+?[0-9]{7,15}$")) {
            throw new BadRequestException("Invalid phone number format.");
        }

        if (userDTO.getAdresse() == null || userDTO.getAdresse().isBlank()) {
            throw new BadRequestException("Address is required.");
        }

        if (userDTO.getRole() == null || (!userDTO.getRole().equals("admin") && !userDTO.getRole().equals("user"))) {
            throw new BadRequestException("Role must be either 'admin' or 'user' (lowercase only).");
        }

    }
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }



    @PutMapping("/updateNbTotal/{iduser}")
    public  UserDTO updatenbtotal(@PathVariable Long iduser,  @RequestParam int newNB)
    {
        UserEntity u = userService.details(iduser);
        u.setNbtotal(u.getNbtotal()+newNB);
        userRepo.save(u);
        UserDTO udto = new UserDTO().ToDTO(u);
        return udto;
    }


}


