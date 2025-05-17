package com.example.project_service.Controllers;

import com.example.project_service.DTO.ProjectDTO;
import com.example.project_service.DTO.UserDTO;
import com.example.project_service.Entities.ProjectEntity;
import com.example.project_service.OpenFeign.UserClient;
import com.example.project_service.Repositories.ProjectRepo;
import com.example.project_service.Services.ProjectService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.hc.client5.http.utils.DateUtils.isBefore;

@RestController
@RequestMapping("/project")
@CrossOrigin("*")
public class ProjectController {
    //injection de dependence = invitation
    @Autowired
    ProjectService projectService;
    @Autowired
    UserClient userClient;
    @Autowired
    private ProjectRepo projectRepo;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/create/{iduser}")
    public ResponseEntity<?> create(@PathVariable Long iduser,ProjectEntity projectEntity) {


        UserDTO x=userClient.showdetails(iduser);
        projectEntity.setIduser(iduser);
        projectEntity.setUserdto(x);
        projectEntity.setState(projectEntity.getState());


        boolean exists = projectRepo.existsByName(projectEntity.getName());
        System.out.println("project name :"+projectEntity.getName());
        System.out.println("exists:"+exists);

        if (exists) {
            System.out.println("exists:"+exists);
            return new ResponseEntity<>("Project already exists", HttpStatus.CONFLICT);
        }
        if (projectEntity.getStartDate().isBefore(projectEntity.getEndDate())  && !exists   ) {
            ProjectEntity newProjectEntity = projectService.create(projectEntity);

            ProjectDTO projectDTO = new ProjectDTO().toDTO(newProjectEntity);
            return new ResponseEntity<>(projectDTO, HttpStatus.CREATED); // 201 Created
        }else {
            return new ResponseEntity<>("Start date must be before end date", HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/AttachUserToProject/{idproject}/{idUser}")
    public ProjectDTO attachUserToProject(@PathVariable Long idproject, @PathVariable Long idUser){
        //recuperation de projet
        ProjectEntity myp=projectService.getById(idproject);
        //recuperation user
        UserDTO user =userClient.showdetails(idUser);
        //attachement user au projet
        myp.setIduser(idUser);
        myp.setUserdto(user);
        projectService.update(myp);
        ProjectDTO finalproject= new ProjectDTO().toDTO(myp);
        finalproject.setIduser(idUser);
        finalproject.setUserdto(user);
        return finalproject;
    }

    @GetMapping("/Listall")
    public List<ProjectDTO> Listall() {

        List<ProjectEntity> listen   = projectService.getAll();
        List<ProjectDTO> projectDTOList=new ArrayList<>();
        for (ProjectEntity x : listen) {
            ProjectDTO mypdto=new ProjectDTO().toDTO(x);
            if(x.getIduser()!=null){
                UserDTO myp = userClient.showdetails(x.getIduser());
                mypdto.setIduser(x.getIduser());
                mypdto.setUserdto(myp);
                mypdto.setState(x.getState());
            }
            else {
                mypdto.setIduser(null);
                mypdto.setUserdto(null);
            }
            projectDTOList.add( mypdto);
        }
        return projectDTOList;
    }

    @GetMapping("/detailProject/{id}")
    public ProjectDTO ShowDetails(@PathVariable Long id) {

        ProjectEntity projectEntity = projectService.getById(id);
        ProjectDTO mypdt = new ProjectDTO().toDTO(projectEntity);
        mypdt.setIduser(projectEntity.getIduser());
        if(projectEntity.getIduser()!=null){
            UserDTO myu = userClient.showdetails(projectEntity.getIduser());
            mypdt.setIduser(projectEntity.getIduser());
            mypdt.setUserdto(myu);

        }
        else {
            mypdt.setIduser(null);
            mypdt.setUserdto(null);
        }
        return mypdt;
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        projectService.delete(id);
    }


    @PutMapping("/update/{id}")
    public ProjectDTO updateProject(@PathVariable Long id, ProjectEntity updatedProject) {

        ProjectEntity project = projectService.getById(id);
        updatedProject.setId(id);
        if(updatedProject.getName() == null){ updatedProject.setName(project.getName()); }
        if(updatedProject.getDescription() == null){ updatedProject.setDescription(project.getDescription()); }
        if(updatedProject.getStartDate() == null){ updatedProject.setStartDate(project.getStartDate()); }
        if(updatedProject.getEndDate() == null){ updatedProject.setEndDate(project.getEndDate()); }
        if(updatedProject.getIduser()==null){ updatedProject.setIduser(project.getIduser()); }

        ProjectEntity a=  projectService.update(updatedProject);
        ProjectDTO mypdto=new ProjectDTO().toDTO(a);
        if(a.getIduser()!=null)
        {
            mypdto.setIduser(a.getIduser());
            mypdto.setUserdto(userClient.showdetails(a.getIduser()));
        }
        else
        {
            mypdto.setIduser(null);
            mypdto.setUserdto(null);
        }
        return mypdto;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/allByUser/{iduser}")
    public List<ProjectDTO> allByUser(@PathVariable Long iduser) {
        List<ProjectEntity> list = projectService.allProjectByUser(iduser);
        List<ProjectDTO> projectDTOList=new ArrayList<>();
        for (ProjectEntity x : list) {
            ProjectDTO mypdto=new ProjectDTO().toDTO(x);
            mypdto.setIduser(x.getIduser());
            UserDTO myp = userClient.showdetails(x.getIduser());
            mypdto.setUserdto(myp);
            mypdto.setState(x.getState());
            projectDTOList.add(mypdto);
        }
        return projectDTOList;
    }
}