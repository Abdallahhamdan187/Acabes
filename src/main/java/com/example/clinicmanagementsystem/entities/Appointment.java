package com.example.clinicmanagementsystem.entities;

import com.example.clinicmanagementsystem.dto.AppointmentStats;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="Appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @Column(nullable = false)
    private LocalTime startTime;
    @Column(nullable = false)
    private LocalTime endTime;
    private AppointmentStats status;
    @ManyToOne
    @JsonIgnore

    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    @ManyToOne
    @JsonIgnore

    @JoinColumn(name = "patientId")
    private Patient patient;

    @ManyToOne
    @JsonIgnore

    @JoinColumn(name = "receptionistId")
    private Receptionist receptionist;

}
