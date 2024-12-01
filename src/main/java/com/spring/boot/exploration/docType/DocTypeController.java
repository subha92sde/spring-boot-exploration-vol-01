package com.spring.boot.exploration.docType;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DocTypeController {
    private final DocTypeService docTypeService;
    
}
