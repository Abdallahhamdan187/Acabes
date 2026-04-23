package com.example.clinicmanagementsystem.exception;

public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException() {
         super("Appointment Not Found!!");
    }

}
