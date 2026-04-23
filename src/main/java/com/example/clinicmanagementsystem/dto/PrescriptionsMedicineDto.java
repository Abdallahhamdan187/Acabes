package com.example.clinicmanagementsystem.dto;

import com.example.clinicmanagementsystem.entities.Medicine;
import com.example.clinicmanagementsystem.entities.Prescription;

public record PrescriptionsMedicineDto(Long id, String dosage, String freq, String durationDays, String instruction,
                                       double price,
                                       Long prescriptionId,Long medicineId) {

}
