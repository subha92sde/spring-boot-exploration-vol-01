package com.spring.boot.exploration.applicant;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_applicant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ssn", unique = true, nullable = false, length = 11)
    private String ssn;

    @Column(name = "password", nullable = false)
    private String password;
}
