package com.spring.boot.exploration.applicant;

import com.spring.boot.exploration.util.DatabaseUtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


@Component
@Order(4)
@RequiredArgsConstructor
public class ApplicantDataLoader implements CommandLineRunner {
    private final ApplicantRepository applicantRepository;
    private final PasswordEncoder passwordEncoder;
    private final DatabaseUtilityService<Applicant> databaseUtilityService;

    @Override
    public void run(String... args) {
        if (databaseUtilityService.isTableEmpty(Applicant.class)) {
            Set<Applicant> applicants = new HashSet<>();
            Random random = new Random();
            for (int i = 1; i <= 30; i++) {
                // Generate a random SSN in the format XXX-XX-XXXX
                String ssn = String.format("%03d-%02d-%04d",
                        random.nextInt(900) + 100,  // Generate a 3-digit number (100-999)
                        random.nextInt(90) + 10,   // Generate a 2-digit number (10-99)
                        random.nextInt(9000) + 1000); // Generate a 4-digit number (1000-9999)

                // Password is "pass" concatenated with the SSN
                String password = "pass" + "-" + ssn;
                String hashedPassword = passwordEncoder.encode(password);

                Applicant applicant = new Applicant();
                applicant.setSsn(ssn);
                applicant.setPassword(hashedPassword);
                applicants.add(applicant);
            }

            // Save all applicants to the database
            applicantRepository.saveAll(applicants);
        } else {
            System.out.println("tbl_applicant already contains data. No action needed.");
        }
    }
}
