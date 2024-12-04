package com.spring.boot.exploration.docType;

import com.spring.boot.exploration.approvalAuthority.ApprovalAuthority;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocTypeService {
    private final DocTypeRepository docTypeRepository;

    public List<DocType> getAllV1() {
        List<DocType> docTypes = docTypeRepository.findAll();
        return docTypes;
    }

    public List<ApprovalAuthority> getApprovalAuthoritiesByDocTypeId(Long docTypeId) {
        List<Object[]> rawResults = docTypeRepository.findApprovalAuthoritiesRawByDocTypeId(docTypeId);
        return rawResults.stream()
                .map(row -> new ApprovalAuthority(
                        ((Long) row[0]).longValue(),
                        (String) row[1],
                        (String) row[2]
                ))
                .collect(Collectors.toList());
    }
}
