package com.example.clinicmanagementsystem.entities;

import com.example.clinicmanagementsystem.dto.InvoiceStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private double totalAmount;

    @Column(nullable = false)
    private double doctorFee;

    @Column(nullable = false)
    private double totalPaid;

    @Column(nullable = false)
    private LocalDate issuedAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    @ManyToMany
    @JoinTable(name = "invoice_medicine",
            joinColumns = @JoinColumn(name = "invoiceId"),
            inverseJoinColumns = @JoinColumn(name = "medicineId"))
    private List<Medicine> medicines;

    @ManyToOne
    @JoinColumn(name = "doctorId")
    @JsonIgnoreProperties({"appointments", "invoices", "prescriptions", "password"})
    @JsonIgnore
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patientId")
    @JsonIgnore
    private Patient patient;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "prescriptionId")
    @JsonIgnore
    private Prescription prescription;
}
