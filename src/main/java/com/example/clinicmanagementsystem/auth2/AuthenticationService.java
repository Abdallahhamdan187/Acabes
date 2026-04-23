package com.example.clinicmanagementsystem.auth2;


import com.example.clinicmanagementsystem.config.JwtService;
import com.example.clinicmanagementsystem.entities.*;
import com.example.clinicmanagementsystem.repo.DoctorRepo;
import com.example.clinicmanagementsystem.repo.PatientRepo;
import com.example.clinicmanagementsystem.repo.ReceptionistRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final DoctorRepo repo;
    private final PatientRepo patientRepo;
    private final ReceptionistRepo receptionistRepo;


    private final PasswordEncoder passwordEncoder;
    private final JwtService service;
    private final AuthenticationManager manager;


    public AuthenticationResponse register(RegisterRequest request) {
        Human user;
        switch (request.getRole()) {
            case Doctor -> {
                user = Doctor.builder()
                        .firstName(request.getFName())
                        .lastName(request.getLName())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .role(Role.Doctor)
                        .build();
                repo.save((Doctor) user);
            }
            case Patient->  {
                user = Patient.builder()
                        .firstName(request.getFName())
                        .lastName(request.getLName())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .role(Role.Patient)
                        .build();
                patientRepo.save((Patient) user);
            }
            case Receptionist -> {
                user = Receptionist.builder()
                        .firstName(request.getFName())
                        .lastName(request.getLName())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .role(Role.Receptionist)
                        .build();
                receptionistRepo.save((Receptionist) user);
            }
            default -> throw new IllegalArgumentException("Unknown user role");
        }

        var jwtToken = service.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse authenticate(AuthRequest request) {

        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );


        Human user = null;

        var doctor = repo.findByEmail(request.getEmail());
        var patient = patientRepo.findByEmail(request.getEmail());
        var receptionist = receptionistRepo.findByEmail(request.getEmail());

        if (doctor.isPresent()) {
            user = doctor.get();
        } else if (patient.isPresent()) {
            user = patient.get();
        } else if (receptionist.isPresent()) {
            user = receptionist.get();
        } else {
            throw new IllegalArgumentException("User not found in any role");
        }

        var jwtToken = service.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
