package com.example.clinicmanagementsystem.exception;

public class MedicineNotFoundException extends RuntimeException {
    public MedicineNotFoundException() {
        super("Medicine Not Found!!");
    }
}
