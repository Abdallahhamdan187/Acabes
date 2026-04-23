package com.example.clinicmanagementsystem.dto;

import com.example.clinicmanagementsystem.entities.Appointment;

import java.util.List;

public record ReceptionistDto(Long id,
                              String firstName,
                              String lastName,
                              String email,
                              int age,
                              String phoneNumber,
                              String userName,
                              String password,
                              List<Long>appointmentId) {
}
