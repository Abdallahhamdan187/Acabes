package com.example.clinicmanagementsystem.exception;

public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException() {
        super("Patient not found!!");
    }
}
