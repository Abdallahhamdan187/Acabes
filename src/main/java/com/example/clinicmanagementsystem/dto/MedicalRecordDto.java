package com.example.clinicmanagementsystem.dto;

import com.example.clinicmanagementsystem.entities.Patient;

import java.time.LocalDate;

public record MedicalRecordDto(
        Long id,
        String chiefComplaint,
        String treatmentPlan,
        LocalDate visitedAt,
        String diagnoses,
        Long patientId
) {}

