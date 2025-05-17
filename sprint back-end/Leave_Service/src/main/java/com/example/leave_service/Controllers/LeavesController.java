package com.example.leave_service.Controllers;

import com.example.leave_service.DTO.LeavesDTO;
import com.example.leave_service.DTO.UserDTO;
import com.example.leave_service.Entities.LeavesEntity;
import com.example.leave_service.Services.LeavesServices;
import com.example.leave_service.openFeign.UserClient;
import com.netflix.discovery.converters.Auto;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Leaves")
@CrossOrigin(origins = "http://localhost:4200")
public class LeavesController {
    @Autowired
    LeavesServices leavesServices;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    UserClient userClient;

//    @PostMapping("/create")
//    public LeavesDTO creation(LeavesEntity leavesEntity) {
//
//       LeavesEntity l=  leavesServices.create(leavesEntity);
//       LeavesDTO ldto =new LeavesDTO().ToDTO(l);
//        return ldto;
//    }

    @PostMapping("/create/{iduser}")

    public LeavesEntity creation(@PathVariable Long iduser,LeavesEntity leavesEntity)  {

        UserDTO x=userClient.showdetails(iduser);
        leavesEntity.setIduser(iduser);
        leavesEntity.setUserDTO(x);
        leavesEntity.setName(x.getUsername());

        System.out.println("******username for user*******"+x.getUsername());
        this.calculateNumberOfDays(leavesEntity);
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("name",x.getUsername());
        data.put("startDate", leavesEntity.getStartDate());
        data.put("endDate",leavesEntity.getEndDate());
        data.put("leaveType", leavesEntity.getLeaveType());
        data.put("status", leavesEntity.getStatus()) ;
        data.put( "numberOfDays",leavesEntity.getNumberOfDays());
        data.put("depositDate",leavesEntity.getDepositDate());
//        if ("OTHER".equalsIgnoreCase(leavesEntity.getLeaveType()) && (leavesEntity.getDescription() == null || leavesEntity.getDescription().isEmpty())) {
//            throw new RuntimeException("Description is required when leave type is OTHER");
//        }

        //camunda
        ProcessInstance processInstance= runtimeService.startProcessInstanceByKey("leaveprocess", data);

        System.out.println("*******************"+ processInstance.getId());
        //Adding the id of the process instance in the DataBase (processInstanceId) into the entity (LeavesEntity)
        leavesEntity.setProcessInstanceId(processInstance.getId());

        return leavesServices.create(leavesEntity);
    }

    @GetMapping("/all")
    public List<LeavesDTO> listtotal() {
        List<LeavesDTO> mylist=new ArrayList<>();

        List<LeavesEntity> lL= leavesServices.All();
        lL.forEach(leavesEntity ->{
                    LeavesDTO ldto = new LeavesDTO();
                    ldto.setId(leavesEntity.getId());
                    ldto.setIduser(leavesEntity.getIduser());
                    ldto.setProcessInstanceId(leavesEntity.getProcessInstanceId());
                    ldto.setName(leavesEntity.getName());
                    ldto.setLeaveType(leavesEntity.getLeaveType());
                    ldto.setNumberOfDays(leavesEntity.getNumberOfDays());
                    ldto.setStatus(leavesEntity.getStatus());
                    ldto.setStartDate(leavesEntity.getStartDate());
                    ldto.setEndDate(leavesEntity.getEndDate());
                    ldto.setUserDTO(userClient.showdetails(leavesEntity.getIduser()));
                    ldto.setDepositDate(leavesEntity.getDepositDate());
                    mylist.add(ldto);

                }


        );

        return mylist ;
    }

    @GetMapping("/details/{id}")
    public LeavesDTO showdetails(@PathVariable Long id) {
        LeavesEntity u = leavesServices.details(id);
        LeavesDTO ldto = new LeavesDTO().ToDTO(u);
        ldto.setUserDTO(userClient.showdetails(u.getIduser()));
        ldto.setIduser(u.getIduser());
        ldto.setProcessInstanceId(u.getProcessInstanceId());
        ldto.setDepositDate(u.getDepositDate());
        if (ldto != null) {
            return ldto;
        } else {
            System.out.println("not found with this id");
            return null;
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")

    @DeleteMapping("/delete/{id}")
    public void suppUser(@PathVariable Long id) {


        leavesServices.Delete(id);
    }

    @PutMapping("/update/{id}")
    public LeavesDTO update(@PathVariable Long id, LeavesEntity V) {

        V.setId(id);//recuperation id leave

        LeavesEntity lastuser = leavesServices.details(id);
        V.setProcessInstanceId(lastuser.getProcessInstanceId());//recuperation idprocess

        V.setStatus(lastuser.getStatus());//recuperation de status
        if(V.getIduser()==null)
        {
            V.setIduser(lastuser.getIduser());
            V.setUserDTO(userClient.showdetails(lastuser.getIduser()));
            V.setName(lastuser.getName());
        }

        if(V.getIduser() !=null)
        {
            V.setIduser(V.getIduser());
            V.setUserDTO(userClient.showdetails(V.getIduser()));
            V.setName(userClient.showdetails(V.getIduser()).getUsername());
        }


        if (V.getLeaveType() == null) {
            V.setLeaveType(lastuser.getLeaveType());
        }
        if (V.getEndDate() == null) {
            V.setEndDate(lastuser.getEndDate());
        }
        if (V.getStartDate() == null) {
            V.setStartDate(lastuser.getStartDate());
        }
        if (V.getDepositDate()==null){
            V.setDepositDate(lastuser.getDepositDate());
        }
        V.setNumberOfDays(this.calculateNumberOfDays(V));
        LeavesEntity l= leavesServices.update(V);
        LeavesDTO ldto = new LeavesDTO().ToDTO(l);
        ldto.setName(l.getName());
        ldto.setProcessInstanceId(l.getProcessInstanceId());
        ldto.setIduser(lastuser.getIduser());
        ldto.setUserDTO(V.getUserDTO());


        //update data on camunda
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("startDate", V.getStartDate());
        data.put("endDate",V.getEndDate());
        data.put("leaveType", V.getLeaveType());
        data.put( "numberOfDays",V.getNumberOfDays());
        String processInstanceId = V.getProcessInstanceId();
        runtimeService.setVariables(processInstanceId, data);


        return ldto;

    }

    @PutMapping("/acceptLeaves/{id}")
    public LeavesDTO acceptLeaves1(@PathVariable Long id) {
        // Get the latest leave details from the service
        LeavesEntity lastleaves = leavesServices.details(id);
        lastleaves.setStatus("Accepted");


        String processInstanceId = lastleaves.getProcessInstanceId();
        if (processInstanceId != null) {
            // Update the process variables using the process instance ID
            HashMap<String, Object> data = new HashMap<>();
            data.put("status", "Accepted");
            // Update process instance variables using the ID
            runtimeService.setVariables(processInstanceId, data);
        }

        // Update the leave entity
        LeavesEntity l = leavesServices.update(lastleaves);
        LeavesDTO ldto = new LeavesDTO().ToDTO(l);
        ldto.setUserDTO(userClient.showdetails(l.getIduser()));
        ldto.setIduser(l.getIduser());
        ldto.setProcessInstanceId(l.getProcessInstanceId());

        //Updated it with processInstanceId
        ldto.setProcessInstanceId(processInstanceId);

        // Return the updated leave information as a DTO
        return ldto;
    }




    @PutMapping("/rejectLeaves/{id}")
    public LeavesDTO rejectLeaves (@PathVariable Long id){
        LeavesEntity lastleaves=leavesServices.details(id);
        lastleaves.setStatus("Rejected");

        String processInstanceId = lastleaves.getProcessInstanceId();
        if (processInstanceId != null) {
            // Update the process variables using the process instance ID
            HashMap<String, Object> data = new HashMap<>();
            data.put("status", "Rejected");
            // Update process instance variables using the ID
            runtimeService.setVariables(processInstanceId, data);
        }

        LeavesEntity l= leavesServices.update(lastleaves);
        LeavesDTO ldto=new LeavesDTO().ToDTO(l);
        ldto.setUserDTO(userClient.showdetails(l.getIduser()));
        ldto.setIduser(l.getIduser());
        ldto.setProcessInstanceId(l.getProcessInstanceId());

        return  ldto;
    }

    @PutMapping("/processLeave/{id}/{iddecision}")
    public LeavesDTO processLeave(@PathVariable Long id, @PathVariable String iddecision) {
        LeavesEntity lastLeaves = leavesServices.details(id);

        if ("accept".equalsIgnoreCase(iddecision)) {
            lastLeaves.setStatus("Accepted");
        } else if ("reject".equalsIgnoreCase(iddecision)) {
            lastLeaves.setStatus("Rejected");
        } else {
            throw new IllegalArgumentException("Invalid decision. Use 'accept' or 'reject'.");
        }

        LeavesEntity updatedLeave = leavesServices.update(lastLeaves);
        return new LeavesDTO().ToDTO(updatedLeave);
    }



    // Method to calculate number of days between start and end date
    public String calculateNumberOfDays(LeavesEntity L) {
        LocalDate start = LocalDate.parse(L.getStartDate());
        LocalDate end = LocalDate.parse(L.getEndDate());
        L.setNumberOfDays( String.valueOf(ChronoUnit.DAYS.between(start, end)+1)); // Include both days
        return L.getNumberOfDays();
    }

    @GetMapping("/decision/{idleave}")
    public String decision(@PathVariable Long idleave){
        LeavesEntity L=leavesServices.details(idleave);
        int caculatednb=Integer.parseInt(L.getNumberOfDays());
        UserDTO myuser=userClient.showdetails(L.getIduser());
        if(myuser.getNbtotal()+caculatednb <myuser.getNbmax()){
            this.acceptLeaves1(idleave);
            userClient.updatenbtotal(L.getIduser(),caculatednb);
        }
        else {
            this.rejectLeaves(idleave);
        }
        return"ok";
    }


    @CrossOrigin(origins = "http://localhost:4200")

    @GetMapping("/allbyiduser/{iduser}")
    public List<LeavesDTO> listtotalbyiduser(@PathVariable Long iduser) {
        List<LeavesDTO> mylist=new ArrayList<>();

        List<LeavesEntity> lL= leavesServices.Allbyiduser(iduser);
        lL.forEach(leavesEntity ->{
                    LeavesDTO ldto = new LeavesDTO();
                    ldto.setId(leavesEntity.getId());
                    ldto.setIduser(leavesEntity.getIduser());
                    ldto.setProcessInstanceId(leavesEntity.getProcessInstanceId());
                    ldto.setName(leavesEntity.getName());
                    ldto.setLeaveType(leavesEntity.getLeaveType());
                    ldto.setNumberOfDays(leavesEntity.getNumberOfDays());
                    ldto.setStatus(leavesEntity.getStatus());
                    ldto.setStartDate(leavesEntity.getStartDate());
                    ldto.setEndDate(leavesEntity.getEndDate());
                    ldto.setDepositDate(leavesEntity.getDepositDate());
                    ldto.setUserDTO(userClient.showdetails(leavesEntity.getIduser()));
                    mylist.add(ldto);

                }


        );

        return mylist ;
    }



}
