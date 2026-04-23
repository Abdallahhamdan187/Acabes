package com.example.clinicmanagementsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PrescriptionsMedicine")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionsMedicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String dosage;
    @Column(nullable = false)
    private String freq;
    @Column(nullable = false)
    private String durationDays;
    @Column(nullable = false)
    private String instruction;
    @Column(nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "preMedId")
    @JsonIgnore
    private Prescription prescription;
    @ManyToOne
    @JoinColumn(name = "medicineId")
    private Medicine medicine;


    public String getLabel(){
        return medicine.getName()+" "+dosage+" "+freq;
    }

}
