package com.example.clinicmanagementsystem.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentDto(Long id, LocalDate date, LocalTime startTime,LocalTime endTime, AppointmentStats stats, Long doctorId, Long patientId,
                             Long receptionistId) {

}

