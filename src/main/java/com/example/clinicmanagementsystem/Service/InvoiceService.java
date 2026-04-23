package com.example.clinicmanagementsystem.Service;

import com.example.clinicmanagementsystem.dto.InvoiceDto;
import com.example.clinicmanagementsystem.dto.InvoiceStatus;
import com.example.clinicmanagementsystem.entities.Doctor;
import com.example.clinicmanagementsystem.entities.Invoice;
import com.example.clinicmanagementsystem.entities.Medicine;
import com.example.clinicmanagementsystem.entities.Patient;
import com.example.clinicmanagementsystem.exception.DoctorNotFoundException;
import com.example.clinicmanagementsystem.exception.InvoiceNotFoundException;
import com.example.clinicmanagementsystem.exception.PatientNotFoundException;
import com.example.clinicmanagementsystem.exception.PrescriptionNotFoundException;
import com.example.clinicmanagementsystem.repo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepo invoiceRepo;
    private final DoctorRepo doctorRepo;
    private final PatientRepo patientRepo;
    private final PrescriptionRepo prescriptionRepo;
    private final MedicineRepo medicineRepo;

    public InvoiceDto addInvoice(@NonNull InvoiceDto dto) {
        Invoice invoice = new Invoice();

        Doctor doctor = doctorRepo.findById(dto.doctorId())
                .orElseThrow(DoctorNotFoundException::new);
        Patient patient = patientRepo.findById(dto.patientId())
                .orElseThrow(PatientNotFoundException::new);

        invoice.setDoctor(doctor);
        invoice.setPatient(patient);

        double doctorFee = dto.doctorFee();
        invoice.setDoctorFee(doctorFee);

        double medicinesSubtotal = 0;
        if (dto.medicineIds() != null && !dto.medicineIds().isEmpty()) {
            List<Medicine> medicineList = medicineRepo.findAllById(dto.medicineIds());
            medicinesSubtotal = medicineList.stream()
                    .mapToDouble(Medicine::getPrice)
                    .sum();
            invoice.setMedicines(medicineList);
        }

        double calculatedTotal = doctorFee + medicinesSubtotal;
        invoice.setTotalAmount(calculatedTotal);

        double actualPaid = Math.min(dto.totalPaid(), calculatedTotal);
        invoice.setTotalPaid(actualPaid);


        invoice.setIssuedAt(dto.issuedAt() != null ? dto.issuedAt() : LocalDate.now());

        invoice.setStatus(invoice.getTotalPaid() >= invoice.getTotalAmount() ? InvoiceStatus.PAID : InvoiceStatus.notPAID);

        if (dto.prescriptionId() != null) {
            invoice.setPrescription(prescriptionRepo.findById(dto.prescriptionId())
                    .orElseThrow(PrescriptionNotFoundException::new));
        }

        Invoice saved = invoiceRepo.save(invoice);

        return new InvoiceDto(
                saved.getId(),
                saved.getTotalAmount(),
                saved.getDoctorFee(),
                saved.getTotalPaid(),
                saved.getIssuedAt(),
                saved.getStatus(),
                saved.getDoctor().getId(),
                saved.getPatient().getId(),
                saved.getPrescription() != null ? saved.getPrescription().getId() : null,
                saved.getMedicines().stream().map(Medicine::getId).toList()

        );
    }


    public Page<Invoice> getFilteredInvoices(int page, int size, InvoiceStatus status, Long pId, Long dId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("issuedAt").descending());
        return invoiceRepo.findByFilters(status, pId, dId, pageable);
    }

    public Invoice getInvoiceById(long id) {
        return invoiceRepo.findById(id).orElseThrow(RuntimeException::new);
    }


    public InvoiceDto updateInvoice(long id, @NonNull InvoiceDto dto) {
        Invoice invoice = invoiceRepo.findById(id)
                .orElseThrow(InvoiceNotFoundException::new);

        invoice.setDoctorFee(dto.doctorFee());
        invoice.setIssuedAt(dto.issuedAt());

        double medicinesSubtotal = 0;
        if (dto.medicineIds() != null) {
            List<Medicine> medicineList = medicineRepo.findAllById(dto.medicineIds());
            medicinesSubtotal = medicineList.stream()
                    .mapToDouble(Medicine::getPrice)
                    .sum();
            invoice.setMedicines(medicineList);
        } else {
            medicinesSubtotal = invoice.getMedicines().stream()
                    .mapToDouble(Medicine::getPrice)
                    .sum();
        }

        double calculatedTotal = invoice.getDoctorFee() + medicinesSubtotal;
        invoice.setTotalAmount(calculatedTotal);

        double actualPaid = Math.min(dto.totalPaid(), calculatedTotal);
        invoice.setTotalPaid(actualPaid);

        invoice.setStatus(invoice.getTotalPaid() >= invoice.getTotalAmount() ? InvoiceStatus.PAID : InvoiceStatus.notPAID);

        if (dto.doctorId() != null) {
            invoice.setDoctor(doctorRepo.findById(dto.doctorId())
                    .orElseThrow(DoctorNotFoundException::new));
        }
        if (dto.patientId() != null) {
            invoice.setPatient(patientRepo.findById(dto.patientId())
                    .orElseThrow(PatientNotFoundException::new));
        }
        if (dto.prescriptionId() != null) {
            invoice.setPrescription(prescriptionRepo.findById(dto.prescriptionId())
                    .orElseThrow(PrescriptionNotFoundException::new));
        }

        Invoice updated = invoiceRepo.save(invoice);

        return new InvoiceDto(
                updated.getId(),
                updated.getTotalAmount(),
                updated.getDoctorFee(),
                updated.getTotalPaid(),
                updated.getIssuedAt(),
                updated.getStatus(),
                updated.getDoctor().getId(),
                updated.getPatient().getId(),
                updated.getPrescription() != null ? updated.getPrescription().getId() : null,
                updated.getMedicines().stream().map(Medicine::getId).toList()
        );
    }

    public void deleteInvoice(long id) {
        Invoice findInvoice = invoiceRepo.findById(id).orElseThrow(RuntimeException::new);
        invoiceRepo.deleteById(id);


    }

    public void markPaid(long id) {
        Invoice findInvoice = invoiceRepo.findById(id).orElseThrow(RuntimeException::new);
        findInvoice.setStatus(InvoiceStatus.PAID);
        invoiceRepo.save(findInvoice);
    }

    public void markUnPaid(long id) {
        Invoice findInvoice = invoiceRepo.findById(id).orElseThrow(RuntimeException::new);
        findInvoice.setStatus(InvoiceStatus.notPAID);
        invoiceRepo.save(findInvoice);
    }


}
