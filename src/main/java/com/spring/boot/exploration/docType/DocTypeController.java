package com.spring.boot.exploration.docType;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/doc-type")
@PreAuthorize("hasRole('APPLICANT')")
public class DocTypeController {
    private final DocTypeService docTypeService;

    @RequestMapping(value = "/get-all/without-pagination", method = RequestMethod.GET)
    public ResponseEntity<?> getAllV1() {
        return ResponseEntity.ok(docTypeService.getAllV1());
    }
}
