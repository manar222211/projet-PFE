package com.example.project_service.OpenFeign;


import com.example.project_service.DTO.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="UserService")
public interface UserClient {
    @GetMapping("User/detail/{id}")
    public UserDTO getById(@PathVariable Long id);


           @GetMapping("/User/details/{id}")
        public UserDTO showdetails(@PathVariable Long id);



    }
