package com.example.clinicmanagementsystem.entities;

import com.example.clinicmanagementsystem.dto.PrescriptionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Prescription")
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate issuedAt;
    @Column(nullable = false)
    private LocalDate validUntil;
    @Column(nullable = false)
    private String instruction;
    @Column(nullable = false)
    private PrescriptionStatus status;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<PrescriptionsMedicine> medicines;


    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "invoiceId")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "appointmentId")
    private Appointment appointment;




}
