package com.example.project_service.DTO;

import com.example.project_service.Entities.ProjectEntity;

import java.time.LocalDate;

public class ProjectDTO {
    private Long id;


    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long iduser;
    private UserDTO userdto ;
    private String state;

    //Getter&Setter

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public UserDTO getUserdto() {
        return userdto;
    }

    public void setUserdto(UserDTO userdto) {
        this.userdto = userdto;
    }
    //constructor

    public ProjectDTO(Long id, String name, String description, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ProjectDTO() {
    }

    //DTO=>Entity
    public ProjectEntity toEntity(ProjectDTO projectDTO) {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(projectDTO.getId());
        projectEntity.setName(projectDTO.getName());
        projectEntity.setDescription(projectDTO.getDescription());
        projectEntity.setStartDate(projectDTO.getStartDate());
        projectEntity.setEndDate(projectDTO.getEndDate());

        projectEntity.setIduser(projectDTO.getIduser());
        projectEntity.setUserdto(projectDTO.getUserdto());
projectEntity.setState(projectDTO.getState());
        return projectEntity;


    }

    //Entity => DTO
    public ProjectDTO toDTO(ProjectEntity projectEntity) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(projectEntity.getId());
        projectDTO.setName(projectEntity.getName());
        projectDTO.setDescription(projectEntity.getDescription());
        projectDTO.setStartDate(projectEntity.getStartDate());
        projectDTO.setEndDate(projectEntity.getEndDate());

        projectDTO.setIduser(projectEntity.getIduser());
        projectDTO.setUserdto(projectEntity.getUserdto());
        projectDTO.setState(projectDTO.getState());

        return projectDTO;

    }
}
