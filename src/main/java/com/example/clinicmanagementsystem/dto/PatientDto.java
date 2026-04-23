package com.example.clinicmanagementsystem.dto;

import com.example.clinicmanagementsystem.entities.Appointment;
import com.example.clinicmanagementsystem.entities.Invoice;
import com.example.clinicmanagementsystem.entities.MedicalRecord;
import com.example.clinicmanagementsystem.entities.Prescription;

import java.time.LocalDate;
import java.util.List;

public record PatientDto(Long id,
                         String firstName,
                         String lastName,
                         String email,
                         int age,
                         String phoneNumber,
                         String userName,
                         String password,LocalDate dob, List<Long> appointmentId, List<Long>invoicesId, List<Long>prescriptionId, List<Long>medicalRecordsId) {
}
