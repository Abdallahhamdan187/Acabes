package com.example.clinicmanagementsystem.exception;

public class ReceptionistNotFoundException extends RuntimeException {
    public ReceptionistNotFoundException() {
        super("Receptionist Not Found !!");
    }
}
