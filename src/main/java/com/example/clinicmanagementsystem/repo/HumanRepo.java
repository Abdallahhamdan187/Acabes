package com.example.clinicmanagementsystem.repo;

import com.example.clinicmanagementsystem.entities.Doctor;
import com.example.clinicmanagementsystem.entities.Human;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HumanRepo extends JpaRepository<Human,Long> {
    Optional<Human> findByEmail(String email);


}
