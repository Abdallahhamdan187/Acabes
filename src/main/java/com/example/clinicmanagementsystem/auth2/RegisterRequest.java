package com.example.clinicmanagementsystem.auth2;

import com.example.clinicmanagementsystem.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String fName;
    private String lName;
    private String email;
    private String password;
    private Role role;


}
