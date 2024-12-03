package com.spring.boot.exploration.applicant;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicantService implements UserDetailsService {
    private final ApplicantRepository applicantRepository;

    @Override
    public UserDetails loadUserByUsername(String ssn) throws UsernameNotFoundException {
        // Validate input to prevent null or empty SSNs
        if (ssn == null || ssn.isEmpty()) {
            throw new UsernameNotFoundException("SSN cannot be null or empty");
        }

        // Fetch the applicant from the repository
        Applicant applicant = applicantRepository.findBySsn(ssn)
                .orElseThrow(() -> new UsernameNotFoundException("Applicant not found with SSN: " + ssn));

        return User.builder()
                .username(applicant.getSsn()) // Use SSN as the username
                .password(applicant.getPassword()) // Hashed password
                .roles("APPLICANT") // Assign APPLICANT role
                .build();
    }
}
