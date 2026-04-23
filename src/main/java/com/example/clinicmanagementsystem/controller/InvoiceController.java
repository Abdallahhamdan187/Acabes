package com.example.clinicmanagementsystem.controller;

import com.example.clinicmanagementsystem.Service.InvoiceService;
import com.example.clinicmanagementsystem.dto.InvoiceDto;
import com.example.clinicmanagementsystem.dto.InvoiceStatus;
import com.example.clinicmanagementsystem.entities.Invoice;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/invoice")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping
    public InvoiceDto addInvoice(@RequestBody InvoiceDto dto) {
        return invoiceService.addInvoice(dto);
    }

    @GetMapping
    public Page<Invoice> getInvoices(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) @Nullable InvoiceStatus status,
            @RequestParam(required = false)@Nullable Long patientId,
            @RequestParam(required = false) @Nullable Long doctorId) {
        return invoiceService.getFilteredInvoices(page, size, status, patientId, doctorId);
    }

    @GetMapping("/{id}")
    public Invoice getById(@PathVariable long id) {
        return invoiceService.getInvoiceById(id);
    }

    @PutMapping("/{id}")
    public InvoiceDto updateInvoice(@PathVariable long id, @RequestBody InvoiceDto dto) {
        return invoiceService.updateInvoice(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteInvoice(@PathVariable long id) {
        invoiceService.deleteInvoice(id);
    }

    @PutMapping("/{id}/mark-paid")
    public void markPaid(@PathVariable long id) {
        invoiceService.markPaid(id);
    }

    @PutMapping("/{id}/mark-unpaid")
    public void markUnPaid(@PathVariable long id) {
        invoiceService.markUnPaid(id);

    }
}
