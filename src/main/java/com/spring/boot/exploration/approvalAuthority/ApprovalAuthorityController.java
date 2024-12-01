package com.spring.boot.exploration.approvalAuthority;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApprovalAuthorityController {
    private final ApprovalAuthorityService approvalAuthorityService;
    
}
