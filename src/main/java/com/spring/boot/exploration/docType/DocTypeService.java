package com.spring.boot.exploration.docType;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocTypeService {
    private final DocTypeRepository docTypeRepository;
    
}
