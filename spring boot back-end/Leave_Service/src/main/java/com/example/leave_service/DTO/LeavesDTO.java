package com.example.leave_service.DTO;

import com.example.leave_service.Entities.LeavesEntity;



public class LeavesDTO {
    private Long id;
    private String startDate;
    private String endDate;
    private  String depositDate ;

    private String leaveType;
    private String status="waiting";
    private String numberOfDays;
    private String name;
    private String processInstanceId;

    private Long iduser;

    private  UserDTO userDTO;
    private String description;

    public String getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(String depositDate) {
        this.depositDate = depositDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(String numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LeavesDTO() {
    }

    public LeavesDTO(Long id, String startDate, String endDate, String leaveType, String status, String numberOfDays, String name) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveType = leaveType;
        this.status = status;
        this.numberOfDays = numberOfDays;
        this.name = name;

    }
    //DTO ==>Entity
    public LeavesEntity ToEntity (LeavesDTO leavesDTO){
        LeavesEntity l = new LeavesEntity();
        l.setId(leavesDTO.getId());
        l.setLeaveType(leavesDTO.getLeaveType());
        l.setStatus(leavesDTO.getStatus());
        l.setStartDate(leavesDTO.getStartDate());
        l.setEndDate(leavesDTO.getEndDate());
        l.setName(leavesDTO.getName());
        l.setNumberOfDays(leavesDTO.getNumberOfDays());
        l.setIduser(leavesDTO.getIduser());
        l.setUserDTO(leavesDTO.getUserDTO());
        l.setDescription(leavesDTO.getDescription());
        l.setDepositDate(leavesDTO.getDepositDate());
        return l;
    }

    //Entity ==>DTO
    public LeavesDTO ToDTO (LeavesEntity leavesEntity){
        LeavesDTO leav = new LeavesDTO();
        leav.setId(leavesEntity.getId());
        leav.setLeaveType(leavesEntity.getLeaveType());
        leav.setStatus(leavesEntity.getStatus());
        leav.setStartDate(leavesEntity.getStartDate());
        leav.setEndDate(leavesEntity.getEndDate());
        leav.setName(leavesEntity.getName());
        leav.setNumberOfDays(leavesEntity.getNumberOfDays());
        leav.setIduser(leavesEntity.getIduser());
        leav.setUserDTO(leavesEntity.getUserDTO());
        leav.setDescription(leavesEntity.getDescription());
       leav.setDepositDate(leavesEntity.getDepositDate());
        return leav;
    }

}
