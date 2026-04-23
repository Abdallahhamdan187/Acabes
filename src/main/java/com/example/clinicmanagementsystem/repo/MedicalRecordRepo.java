package com.example.clinicmanagementsystem.repo;

import com.example.clinicmanagementsystem.entities.MedicalRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicalRecordRepo extends JpaRepository<MedicalRecord, Long> {

    @Query("SELECT m FROM MedicalRecord m WHERE m.patient.id = :patientId")
    List<MedicalRecord> findByPatientId(long patientId);

    @Query("SELECT m FROM MedicalRecord m WHERE " +
            "(:diagnoses IS NULL OR m.diagnoses LIKE %:diagnoses%) AND " +
            "(:patientId IS NULL OR m.patient.id = :patientId)")
    Page<MedicalRecord> findWithFilters(
            @Param("diagnoses") String diagnoses,
            @Param("patientId") Long patientId,
            Pageable pageable);
}



