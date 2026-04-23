package com.example.clinicmanagementsystem.exception;

public class DoctorNotFoundException extends RuntimeException {
    public DoctorNotFoundException() {
         super("Doctor not found!!!");
    }
}


