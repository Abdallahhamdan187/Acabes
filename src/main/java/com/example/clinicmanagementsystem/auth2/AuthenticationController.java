package com.example.clinicmanagementsystem.auth2;

import com.example.clinicmanagementsystem.AuthService.AuthDoctorService;
import com.example.clinicmanagementsystem.RegisterRequest.RegisterDoctorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private  final AuthDoctorService authDoctorService;
private final AuthenticationService authenticate;
    @PostMapping("/register/doctor")
    public ResponseEntity<AuthenticationResponse> registerDoctor(
            @RequestBody RegisterDoctorRequest request) {
        return ResponseEntity.ok(authDoctorService.registerDoctor(request));
    }

//    @PostMapping("/register/patient")
//    public ResponseEntity<AuthenticationResponse> registerPatient(
//            @RequestBody RegisterPatientRequest request) {
//        return ResponseEntity.ok(service.registerPatient(request));
//    }
//
//    @PostMapping("/register/receptionist")
//    public ResponseEntity<AuthenticationResponse> registerReceptionist(
//            @RequestBody RegisterReceptionistRequest request) {
//        return ResponseEntity.ok(service.registerReceptionist(request));
//    }
//

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authenticate.authenticate(request));
    }

}
