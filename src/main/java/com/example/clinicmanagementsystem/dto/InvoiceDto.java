package com.example.clinicmanagementsystem.dto;

import java.time.LocalDate;
import java.util.List;

public record InvoiceDto(
        Long id,
        double totalAmount,
        double doctorFee,
        double totalPaid,
        LocalDate issuedAt,
        InvoiceStatus status,
        Long doctorId,
        Long patientId,
        Long prescriptionId,
        List<Long> medicineIds
) {
}

