package com.spring.boot.exploration.approvalAuthority;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApprovalAuthorityService {
    private final ApprovalAuthorityRepository approvalAuthorityRepository;
    
}
