package com.example.clinicmanagementsystem.Service;

import com.example.clinicmanagementsystem.dto.MedicineDto;
import com.example.clinicmanagementsystem.entities.Medicine;
import com.example.clinicmanagementsystem.exception.MedicineNotFoundException;
import com.example.clinicmanagementsystem.repo.MedicineRepo;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicineService {

    private final MedicineRepo medicineRepo;

    public MedicineDto addMedicine(@NonNull MedicineDto dto) {
        Medicine medicine = new Medicine();
        medicine.setName(dto.name());
        medicine.setCategory(dto.category());
        medicine.setUnit(dto.unit());
        medicine.setDescription(dto.description());
        medicine.setPrice(dto.price());

        Medicine saved = medicineRepo.save(medicine);
        return convertToDto(saved);
    }

    public List<MedicineDto> getAllMedicines() {
        return medicineRepo.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public MedicineDto getMedicineById(Long id) {
        Medicine medicine = medicineRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found"));
        return convertToDto(medicine);
    }

    public MedicineDto updateMedicine(Long id, @NonNull MedicineDto dto) {
        Medicine medicine = medicineRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found"));

        medicine.setName(dto.name());
        medicine.setCategory(dto.category());
        medicine.setUnit(dto.unit());
        medicine.setDescription(dto.description());
        medicine.setPrice(dto.price());

        Medicine updated = medicineRepo.save(medicine);
        return convertToDto(updated);
    }

    public void deleteMedicine(Long id) {
        if (!medicineRepo.existsById(id)) {
            throw new MedicineNotFoundException();
        }
        medicineRepo.deleteById(id);

    }

    private MedicineDto convertToDto(Medicine medicine) {
        return new MedicineDto(
                medicine.getId(),
                medicine.getName(),
                medicine.getCategory(),
                medicine.getUnit(),
                medicine.getDescription(),
                medicine.getPrice(),
                null
        );
    }
}
