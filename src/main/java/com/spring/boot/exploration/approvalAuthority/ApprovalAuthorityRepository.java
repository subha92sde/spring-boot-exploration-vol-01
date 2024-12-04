package com.spring.boot.exploration.approvalAuthority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprovalAuthorityRepository extends JpaRepository<ApprovalAuthority, Long> {
    
}
