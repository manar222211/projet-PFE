package com.example.user_service.Services;


import com.example.user_service.DTO.UserDTO;
import com.example.user_service.Entities.UserEntity;
import com.example.user_service.Repositories.UserRepo;
import jakarta.mail.MessagingException;
import jakarta.ws.rs.BadRequestException;
import org.hibernate.validator.internal.util.DomainNameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class UserService {

    //visibilite typeDeRetour NomFonction (Typeparam1 parm1, typeparm2 parm2 ,.....)
    //public     int             addition  (int a,int b)


    //injection de dependance
    @Autowired
    UserRepo userRepo;
    public UserEntity create(UserEntity u)
    {return  userRepo.save(u);}

    public UserEntity update(UserEntity u){
        return userRepo.save(u);
    }

    public UserEntity details(Long id){
        return userRepo.findById(id).orElse(null);
    }

    public List<UserEntity> All(){
        return userRepo.findAll();
    }
    public void Delete (Long id){
        userRepo.deleteById(id);
    }

    public List<UserEntity> AllByRole(String role){
        return userRepo.findAllByRole(role);
    }






    }


