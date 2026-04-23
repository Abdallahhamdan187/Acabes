package com.example.clinicmanagementsystem.repo;

import com.example.clinicmanagementsystem.entities.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepo extends JpaRepository<Medicine,Long> {
}
