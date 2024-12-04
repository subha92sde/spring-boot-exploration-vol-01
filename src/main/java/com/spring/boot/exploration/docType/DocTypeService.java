package com.spring.boot.exploration.docType;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocTypeService {
    private final DocTypeRepository docTypeRepository;

    public List<DocType> getAllV1() {
        List<DocType> docTypes = docTypeRepository.findAll();
        return docTypes;
    }
}
