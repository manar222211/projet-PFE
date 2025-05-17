package com.example.leave_service.openFeign;

import com.example.leave_service.DTO.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="UserService")
public interface UserClient {
    @GetMapping("/User/details/{id}")
    public UserDTO showdetails(@PathVariable Long id);

    @PutMapping("/User/updateNbTotal/{iduser}")
    public  UserDTO updatenbtotal(@PathVariable Long iduser, @RequestParam int newNB);

}
