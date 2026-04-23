package com.example.clinicmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication
@EntityScan({"com.example.clinicmanagementsystem"})
public class ClinicManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicManagementSystemApplication.class, args);
    }

}
