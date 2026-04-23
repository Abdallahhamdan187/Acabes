package com.example.clinicmanagementsystem.RegisterRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDoctorRequest {


   private String firstName;
    private  String lastName;
    private  String email;
    private int age;
    private String phoneNumber;
    private  String userName;
    String password;
    private  LocalTime startTime;
    private  LocalTime endTime;
    private String specialization;
}
