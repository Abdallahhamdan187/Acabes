package com.example.clinicmanagementsystem.repo;

import com.example.clinicmanagementsystem.dto.PrescriptionStatus;
import com.example.clinicmanagementsystem.entities.Prescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PrescriptionRepo extends JpaRepository<Prescription,Long> {

    @Query("SELECT p FROM Prescription p WHERE p.patient.id = :patientId AND p.validUntil > CURRENT_DATE")
    List<Prescription> findAllActivePrescriptionsByPatientId(long patientId);


    @Query("SELECT p FROM Prescription p WHERE p.id = :id AND p.validUntil > CURRENT_DATE")
    Optional<Prescription> findIfValid(long id);



    @Query("SELECT p FROM Prescription p WHERE " +
            "(:status IS NULL OR p.status = :status) AND " +
            "(:patientId IS NULL OR p.patient.id = :patientId) AND " +
            "(:doctorId IS NULL OR p.doctor.id = :doctorId) AND " +
            "(:startDate IS NULL OR p.issuedAt >= :startDate) AND " +
            "(:endDate IS NULL OR p.issuedAt <= :endDate)")
    Page<Prescription> findByFilters(
            @Param("status") PrescriptionStatus status,
            @Param("patientId") Long patientId,
            @Param("doctorId") Long doctorId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable);

}
