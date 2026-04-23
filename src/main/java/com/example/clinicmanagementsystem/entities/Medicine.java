package com.example.clinicmanagementsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Medicine")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String unit;
    private String description;
    @Column(nullable = false)
    private double price;

    @OneToMany(mappedBy = "medicine",orphanRemoval = true)
    @JsonIgnore
    private List<PrescriptionsMedicine> prescriptionsMedicine;


}
