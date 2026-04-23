package com.example.clinicmanagementsystem.Service;

import com.example.clinicmanagementsystem.dto.MedicalRecordDto;
import com.example.clinicmanagementsystem.entities.MedicalRecord;
import com.example.clinicmanagementsystem.exception.MedicalRecordException;
import com.example.clinicmanagementsystem.repo.PatientRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.clinicmanagementsystem.repo.MedicalRecordRepo;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class MedialRecordService {
    private final MedicalRecordRepo medicalRecordRepo;
    private final PatientRepo  patientRepo;

    public MedicalRecordDto addMedicalRecord(@NonNull MedicalRecordDto dto) {
        MedicalRecord record = new MedicalRecord();

        record.setChiefComplaint(dto.chiefComplaint());
        record.setTreatmentPlan(dto.treatmentPlan());
        record.setVisitedAt(dto.visitedAt() != null ? dto.visitedAt() : LocalDate.now());
        record.setDiagnoses(dto.diagnoses());

        record.setPatient(patientRepo.findById(dto.patientId())
                .orElseThrow(() -> new RuntimeException("Patient not found")));

        MedicalRecord saved = medicalRecordRepo.save(record);

        return new MedicalRecordDto(
                saved.getId(),
                saved.getChiefComplaint(),
                saved.getTreatmentPlan(),
                saved.getVisitedAt(),
                saved.getDiagnoses(),
                saved.getPatient().getId()
        );
    }

    public Page<MedicalRecord> findWithFilters(int page, int size, String diagnosis, Long patientId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        return medicalRecordRepo.findWithFilters(diagnosis, patientId, pageable);
    }

    public MedicalRecord getMedicalRecordById(long id) {
        return medicalRecordRepo.findById(id).orElseThrow(MedicalRecordException::new);
    }

    public MedicalRecordDto updateMedicalRecord(long id, @NonNull MedicalRecordDto dto) {
        MedicalRecord record = medicalRecordRepo.findById(id)
                .orElseThrow(MedicalRecordException::new);

        record.setChiefComplaint(dto.chiefComplaint());
        record.setTreatmentPlan(dto.treatmentPlan());
        record.setVisitedAt(dto.visitedAt());
        record.setDiagnoses(dto.diagnoses());

        if (dto.patientId() != null) {
            record.setPatient(patientRepo.findById(dto.patientId())
                    .orElseThrow(() -> new RuntimeException("Patient not found")));
        }

        MedicalRecord updated = medicalRecordRepo.save(record);

        return new MedicalRecordDto(
                updated.getId(),
                updated.getChiefComplaint(),
                updated.getTreatmentPlan(),
                updated.getVisitedAt(),
                updated.getDiagnoses(),
                updated.getPatient().getId()
        );
    }


    public void deleteMedicalRecord(long id) {
        MedicalRecord findMedicalRecord = medicalRecordRepo.findById(id).orElseThrow(MedicalRecordException::new);
        medicalRecordRepo.deleteById(id);

    }


}
