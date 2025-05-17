package com.example.leave_service;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

import java.util.HashMap;

@SpringBootApplication
@EnableProcessApplication
@EnableDiscoveryClient
@EnableFeignClients


public class LeaveServiceApplication {

    @Autowired
    private RuntimeService runtimeService;

    public static void main(String[] args) {
        SpringApplication.run(LeaveServiceApplication.class, args);


    }

    @EventListener
    private void processPostDeploy(PostDeployEvent event) {





//        runtimeService.startProcessInstanceByKey("leaveprocess", data);
    }
}