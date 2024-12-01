package com.spring.boot.exploration.util;

import com.spring.boot.exploration.approvalAuthority.ApprovalAuthority;
import com.spring.boot.exploration.approvalAuthority.ApprovalAuthorityRepository;
import com.spring.boot.exploration.docType.DocType;
import com.spring.boot.exploration.docType.DocTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.Collections;

@Component
@Order(3)
@RequiredArgsConstructor
public class DocTypeApprovalAuthorityDataLoader implements CommandLineRunner {
    private final DocTypeRepository docTypeRepository;
    private final ApprovalAuthorityRepository approvalAuthorityRepository;
    private static final String JOIN_TABLE_NAME = "tbl_doc_type_approval_authority";
    private final DatabaseUtilityService databaseUtilityService;

    @Override
    @Transactional
    public void run(String... args) {
        // Check if the join table is empty
        if (databaseUtilityService.isJoinTableEmpty(JOIN_TABLE_NAME)) {
            System.out.println("The " + JOIN_TABLE_NAME + " table is empty. Starting data loading...");

            // Fetch all document types and approval authorities from the database
            List<DocType> docTypes = docTypeRepository.findAll();
            Map<String, ApprovalAuthority> authorities = approvalAuthorityRepository.findAll().stream()
                    .collect(Collectors.toMap(ApprovalAuthority::getTitleKey, authority -> authority));

            // Get the mapping of document types to approval authorities
            Map<String, Set<String>> docTypeToAuthorities = getDocTypeToAuthorities();

            // Assign authorities to each document type
            for (DocType docType : docTypes) {
                Set<String> authorityKeys = docTypeToAuthorities.getOrDefault(docType.getTitleKey(), Collections.emptySet());
                Set<ApprovalAuthority> selectedAuthorities = new HashSet<>();

                for (String key : authorityKeys) {
                    ApprovalAuthority authority = authorities.get(key);
                    if (authority != null) {
                        selectedAuthorities.add(authority);

                        // Ensure reverse mapping is also updated
                        if (authority.getDocTypeSet() != null) {
                            authority.getDocTypeSet().add(docType);
                        }
                    } else {
                        System.err.println("Warning: ApprovalAuthority with titleKey '" + key + "' not found in database.");
                    }
                }

                docType.setApprovalAuthoritySet(selectedAuthorities);
            }

            // Save the updated relationships back to the database
            docTypeRepository.saveAll(docTypes);
            System.out.println("Document types and their approval authorities have been successfully associated.");
        } else {
            System.out.println("The " + JOIN_TABLE_NAME + " table already contains data. No action taken.");
        }
    }

    private Map<String, Set<String>> getDocTypeToAuthorities() {
        Map<String, Set<String>> docTypeToAuthorities = new HashMap<>();

        docTypeToAuthorities.put("government_land_lease_deed", Set.of(
                "document_clerk", "assistant_document_clerk", "land_surveyor",
                "revenue_compliance_inspector", "junior_land_records_officer"
        ));
        docTypeToAuthorities.put("land_acquisition_order", Set.of(
                "district_magistrate_collector", "land_revenue_commissioner",
                "sub_divisional_magistrate"
        ));
        docTypeToAuthorities.put("land_conversion_certificate", Set.of(
                "document_clerk", "revenue_compliance_inspector",
                "administrative_block_officer", "sub_divisional_magistrate",
                "senior_land_records_officer"
        ));
        docTypeToAuthorities.put("land_title_deed", Set.of(
                "junior_land_records_officer", "senior_land_records_officer",
                "administrative_block_officer", "revenue_compliance_inspector",
                "land_revenue_commissioner"
        ));
        docTypeToAuthorities.put("development_permission", Set.of(
                "document_clerk", "assistant_document_clerk", "land_surveyor",
                "administrative_block_officer", "district_magistrate_collector"
        ));
        docTypeToAuthorities.put("large_infrastructure_project_approval", Set.of(
                "land_revenue_commissioner", "district_magistrate_collector",
                "senior_land_records_officer", "revenue_compliance_inspector",
                "sub_divisional_magistrate"
        ));
        docTypeToAuthorities.put("forest_land_diversion_approval", Set.of(
                "sub_divisional_magistrate", "district_magistrate_collector",
                "land_revenue_commissioner", "senior_land_records_officer",
                "document_clerk"
        ));
        docTypeToAuthorities.put("mining_lease_agreement", Set.of(
                "document_clerk", "revenue_compliance_inspector",
                "administrative_block_officer", "land_revenue_commissioner",
                "assistant_document_clerk"
        ));
        docTypeToAuthorities.put("eviction_and_resettlement_order", Set.of(
                "district_magistrate_collector", "sub_divisional_magistrate",
                "land_revenue_commissioner", "revenue_compliance_inspector",
                "administrative_block_officer"
        ));
        docTypeToAuthorities.put("unauthorized_land_use_regularization", Set.of(
                "administrative_block_officer", "land_revenue_commissioner",
                "revenue_compliance_inspector", "document_clerk",
                "junior_land_records_officer"
        ));
        docTypeToAuthorities.put("land_subdivision_approval", Set.of(
                "land_surveyor", "administrative_block_officer",
                "revenue_compliance_inspector", "senior_land_records_officer",
                "district_magistrate_collector"
        ));
        docTypeToAuthorities.put("property_tax_clearance_certificate", Set.of(
                "revenue_compliance_inspector", "assistant_document_clerk",
                "document_clerk", "land_revenue_commissioner", "administrative_block_officer"
        ));
        docTypeToAuthorities.put("building_plan_approval", Set.of(
                "document_clerk", "assistant_document_clerk", "land_surveyor",
                "administrative_block_officer", "district_magistrate_collector"
        ));
        docTypeToAuthorities.put("environmental_clearance_certificate", Set.of(
                "sub_divisional_magistrate", "district_magistrate_collector",
                "land_revenue_commissioner", "senior_land_records_officer",
                "document_clerk"
        ));
        docTypeToAuthorities.put("zoning_certificate", Set.of(
                "administrative_block_officer", "district_magistrate_collector",
                "land_revenue_commissioner", "document_clerk", "revenue_compliance_inspector"
        ));
        docTypeToAuthorities.put("land_transfer_deed", Set.of(
                "junior_land_records_officer", "senior_land_records_officer",
                "administrative_block_officer", "revenue_compliance_inspector",
                "land_revenue_commissioner"
        ));
        docTypeToAuthorities.put("land_allotment_certificate", Set.of(
                "document_clerk", "assistant_document_clerk", "administrative_block_officer",
                "district_magistrate_collector", "land_revenue_commissioner"
        ));
        docTypeToAuthorities.put("right_of_way_authorization", Set.of(
                "administrative_block_officer", "sub_divisional_magistrate",
                "district_magistrate_collector", "revenue_compliance_inspector",
                "land_revenue_commissioner"
        ));
        docTypeToAuthorities.put("boundary_demarcation_certificate", Set.of(
                "land_surveyor", "junior_land_records_officer",
                "senior_land_records_officer", "administrative_block_officer",
                "district_magistrate_collector"
        ));
        docTypeToAuthorities.put("renewal_of_land_lease_agreement", Set.of(
                "document_clerk", "revenue_compliance_inspector",
                "administrative_block_officer", "land_revenue_commissioner",
                "assistant_document_clerk"
        ));

        return docTypeToAuthorities;
    }
}
