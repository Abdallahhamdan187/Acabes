package com.example.clinicmanagementsystem.dto;

import com.example.clinicmanagementsystem.entities.Appointment;
import com.example.clinicmanagementsystem.entities.Invoice;
import com.example.clinicmanagementsystem.entities.Prescription;

import java.time.LocalTime;
import java.util.List;

public record DoctorDto(Long id,
                        String firstName,
                        String lastName,
                        String email,
                        int age,
                        String phoneNumber,
                        String userName,
                        String password,
                        LocalTime startTime, LocalTime endTime, String specialization,
                        List<Long> appointmentId, List<Long> prescriptionId,
                        Long invoiceId) {
}
