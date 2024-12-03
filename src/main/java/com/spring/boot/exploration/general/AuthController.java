package com.spring.boot.exploration.general;

import com.spring.boot.exploration.applicant.ApplicantLoginReqDTO;
import com.spring.boot.exploration.applicant.ApplicantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth/login")
@RequiredArgsConstructor
public class AuthController {
    private final ApplicantService applicantService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/applicant")
    public ResponseEntity<?> loginApplicant(@RequestBody @Valid ApplicantLoginReqDTO loginRequest) {
        UserDetails applicantDetails = applicantService.loadUserByUsername(loginRequest.getSsn());
        if (!passwordEncoder.matches(loginRequest.getPassword(), applicantDetails.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        String token = jwtUtil.generateToken(applicantDetails.getUsername());
        return ResponseEntity.ok(Map.of("token", token));
    }
}
