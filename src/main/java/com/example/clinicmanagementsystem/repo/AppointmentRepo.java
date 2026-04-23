package com.example.clinicmanagementsystem.repo;

import com.example.clinicmanagementsystem.dto.AppointmentStats;
import com.example.clinicmanagementsystem.entities.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Long> {
    @Query("SELECT COUNT(a) > 0 FROM Appointment a WHERE a.doctor.id = :doctorId " +
            "AND a.date = :date " +
            "AND a.status <> :status " +
            "AND a.startTime < :endTime " +
            "AND a.endTime > :startTime")
    boolean hasConflict(
            @Param("doctorId") Long doctorId,
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime,
            @Param("status") AppointmentStats status
    );

    


    @Query("SELECT a FROM Appointment a WHERE " +
            "a.doctor.id = :id AND " +
            "a.date BETWEEN :startDate AND :endDate")
    List<Appointment> getAppointmentsFromRange(
            @Param("id") long id,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
    @Query("SELECT a FROM Appointment a WHERE " +
            "a.doctor.id = :id ")
    List<Appointment> getSchedule(
            @Param("id") long id

    );



}