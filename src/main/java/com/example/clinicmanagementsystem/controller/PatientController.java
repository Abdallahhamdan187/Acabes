package com.example.clinicmanagementsystem.controller;

import com.example.clinicmanagementsystem.Service.PatientService;
import com.example.clinicmanagementsystem.dto.InvoiceStatus;
import com.example.clinicmanagementsystem.entities.Invoice;
import com.example.clinicmanagementsystem.entities.MedicalRecord;
import com.example.clinicmanagementsystem.entities.Prescription;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/{id}/activePres")
    public List<Prescription> getActivePrescription(@PathVariable long id) {
        return patientService.getActivePrescription(id);
    }

    @GetMapping("/{id}/medicalHis")
    public List<MedicalRecord> getMedicalHistory(@PathVariable long id) {
        return patientService.getMedicalHistory(id);
    }

    @GetMapping("/{id}/unPaidInv")
    public List<Invoice> getUnPaidInvoices(@PathVariable long id) {
        return patientService.getUnPaidInvoices(id);
    }
}
