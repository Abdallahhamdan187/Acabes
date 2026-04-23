package com.example.clinicmanagementsystem.exception;

public class PrescriptionNotFoundException extends RuntimeException {
    public PrescriptionNotFoundException() {
        super("Prescription not found!!");
    }
}
