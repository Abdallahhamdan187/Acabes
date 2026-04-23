package com.example.clinicmanagementsystem.repo;

import com.example.clinicmanagementsystem.dto.InvoiceStatus;
import com.example.clinicmanagementsystem.entities.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceRepo extends JpaRepository<Invoice, Long> {

    @Query("SELECT i FROM Invoice i WHERE i.patient.id = :patientId AND i.status = :status")
    List<Invoice> getUnPaidInvoicesByPatientId(@Param("patientId") long patientId, @Param("status") InvoiceStatus status);

    // 2. Added explicit query for totalAmount
    @Query("SELECT COALESCE(SUM(i.totalAmount), 0) FROM Invoice i WHERE i.doctor.id = :doctorId")
    Integer sumAmountByDoctorId(@Param("doctorId") long doctorId);

    @Query("SELECT COALESCE(SUM(i.totalAmount), 0.0) FROM Invoice i " +
            "WHERE i.doctor.id = :id AND i.issuedAt = CURRENT_DATE")
    double sumTodayEarningByDoctorId(@Param("id") long id);

    // 3. Added space between query lines for safety
    @Query("SELECT i FROM Invoice i WHERE " +
            "(:status IS NULL OR i.status = :status) AND " +
            "(:patientId IS NULL OR i.patient.id = :patientId) AND " + // Added space here
            "(:doctorId IS NULL OR i.doctor.id = :doctorId)")
    Page<Invoice> findByFilters(
            @Param("status") InvoiceStatus status,
            @Param("patientId") Long patientId,
            @Param("doctorId") Long doctorId,
            Pageable pageable);

    @Query("SELECT COALESCE(SUM(i.totalAmount), 0.0) FROM Invoice i " +
            "WHERE i.doctor.id = :id " +
            "AND i.issuedAt BETWEEN :startDate AND :endDate")
    double sumEarningsByDoctorAndRange(
            @Param("id") long id,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

}
