package com.example.clinicmanagementsystem.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter@Setter@Entity@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String chiefComplaint;
    @Column(nullable = false)
    private String treatmentPlan;
    @Column(nullable = false)
    private LocalDate visitedAt;
    @Column(nullable = false)
    private String diagnoses;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

}
