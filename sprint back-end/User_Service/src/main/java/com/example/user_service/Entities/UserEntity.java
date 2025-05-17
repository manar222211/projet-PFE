package com.example.user_service.Entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity

public class UserEntity {


    //attribues //variables
    //visibilité type non variable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String username;
    private String email;
    private String password;
    private String phone;
    private String photo;
    private String role;
    private String adresse;

    private String firstname;
    private String lastname;
    private boolean confirm = false;
    private String passwordResetToken;

    private int nbmax;
    private int nbtotal = 0;
    private String skills;
    private Boolean placed;
    private Integer nbexperience;

    private String grade;

    private Double salary;
    private String typeofContract;
    private String maritalStatus;
    private String extraHours;

    private String department;


    private String gender;


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getTypeofContract() {
        return typeofContract;
    }

    public void setTypeofContract(String typeofContract) {
        this.typeofContract = typeofContract;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getExtraHours() {
        return extraHours;
    }

    public void setExtraHours(String extraHours) {
        this.extraHours = extraHours;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public Boolean getPlaced() {
        return placed;
    }

    public void setPlaced(Boolean placed) {
        this.placed = placed;
    }

    public Integer getNbexperience() {
        return nbexperience;
    }

    public void setNbexperience(Integer nbexperience) {
        this.nbexperience = nbexperience;
    }

    public String getPasswordResetToken() {
        return passwordResetToken;
    }

    public int getNbmax() {
        return nbmax;
    }

    public void setNbmax(int nbmax) {
        this.nbmax = nbmax;
    }

    public int getNbtotal() {
        return nbtotal;
    }

    public void setNbtotal(int nbtotal) {
        this.nbtotal = nbtotal;
    }

    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

//fonction + getter setter+constracteurs

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    //ajoute constrocteurs
    public UserEntity() {

    }

    public UserEntity(Long id, String username, String email, String password, String phone, String photo, String role, String adresse, String firstname, String lastname, String grade, Double salary, String typeofContract, String maritalStatus, String extraHours, String department, String gender) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.photo = photo;
        this.role = role;
        this.adresse = adresse;
        this.firstname = firstname;
        this.lastname = lastname;
        this.grade = grade;
        this.salary = salary;
        this.typeofContract = typeofContract;
        this.maritalStatus = maritalStatus;
        this.extraHours = extraHours;
        this.department = department;
        this.gender = gender;
    }


}