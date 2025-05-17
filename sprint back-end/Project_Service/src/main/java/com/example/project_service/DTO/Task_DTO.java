package com.example.project_service.DTO;

//import com.example.task_service.Entities.Task_Entity;


public class Task_DTO {

        private Long id;
        private String name;
        private String description;
        private int period;
        private int priority;

        //getter&setter

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

        public int getPeriod() {
            return period;
        }

        public void setPeriod(int period) {
            this.period = period;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        //constructor

        public Task_DTO(Long id, String name, String description, int period, int priority) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.period = period;
            this.priority = priority;
        }

        public Task_DTO() {
        }



}







