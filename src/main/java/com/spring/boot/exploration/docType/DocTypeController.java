package com.spring.boot.exploration.docType;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doc-type")
@RequiredArgsConstructor
public class DocTypeController {
    private final DocTypeService docTypeService;

    @RequestMapping(value = "/get-all/without-pagination", method = RequestMethod.GET)
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<?> getAllV1() {
        return ResponseEntity.ok(docTypeService.getAllV1());
    }

    @RequestMapping(value = "/get-approval-authorities-by-doc-type-id/{docTypeId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<?> getApprovalAuthorities(@PathVariable Long docTypeId) {
        return ResponseEntity.ok(docTypeService.getApprovalAuthoritiesByDocTypeId(docTypeId));
    }
}
