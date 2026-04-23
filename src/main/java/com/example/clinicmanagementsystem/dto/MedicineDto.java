package com.example.clinicmanagementsystem.dto;

import com.example.clinicmanagementsystem.entities.PrescriptionsMedicine;

public record MedicineDto(Long id, String name, String category, String unit, String description,double price,
                          Long prescriptionsMedicineId) {
}
