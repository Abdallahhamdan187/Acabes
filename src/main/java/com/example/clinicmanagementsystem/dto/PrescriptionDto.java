package com.example.clinicmanagementsystem.dto;

import com.example.clinicmanagementsystem.entities.*;

import java.time.LocalDate;
import java.util.List;

public record PrescriptionDto(Long id, LocalDate issuedAt, LocalDate validUntil, String instruction,
                               Long invoiceId,
                              Long doctorId, Long patientId, Long appointmentId,
                              List<PrescriptionsMedicineDto> medicines) {
}
