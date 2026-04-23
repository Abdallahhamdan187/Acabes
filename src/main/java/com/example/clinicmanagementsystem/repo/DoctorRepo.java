package com.example.clinicmanagementsystem.repo;

import com.example.clinicmanagementsystem.entities.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {
    @Query("SELECT d FROM Doctor d WHERE " +
            "(:spec IS NULL OR d.specialization = :spec) AND " +
            "(:name IS NULL OR LOWER(d.firstName) like LOWER(CONCAT('%', :name, '%')) OR " +
            "LOWER(d.lastName) like LOWER(CONCAT('%', :name, '%')))")
    Page<Doctor> findByFilters(
            @Param("spec") String specialization,
            @Param("name") String name,
            Pageable pageable);

    Optional<Doctor> findByEmail(String email);
}
