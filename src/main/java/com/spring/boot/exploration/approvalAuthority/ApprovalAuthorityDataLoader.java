package com.spring.boot.exploration.approvalAuthority;

import com.spring.boot.exploration.util.DatabaseUtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Order(2)
@RequiredArgsConstructor
public class ApprovalAuthorityDataLoader implements CommandLineRunner {
    private final ApprovalAuthorityRepository approvalAuthorityRepository;
    private final DatabaseUtilityService<ApprovalAuthority> databaseUtilityService;

    @Override
    public void run(String... args) {
        if (databaseUtilityService.isTableEmpty(ApprovalAuthority.class)) {
            List<ApprovalAuthority> authorities = Arrays.asList(
                    new ApprovalAuthority(1L, "Document Clerk", "document_clerk"),
                    new ApprovalAuthority(2L, "Assistant Document Clerk", "assistant_document_clerk"),
                    new ApprovalAuthority(3L, "Land Surveyor", "land_surveyor"),
                    new ApprovalAuthority(4L, "Junior Land Records Officer", "junior_land_records_officer"),
                    new ApprovalAuthority(5L, "Revenue Compliance Inspector", "revenue_compliance_inspector"),
                    new ApprovalAuthority(6L, "Senior Land Records Officer", "senior_land_records_officer"),
                    new ApprovalAuthority(7L, "Administrative Block Officer", "administrative_block_officer"),
                    new ApprovalAuthority(8L, "Sub-Divisional Magistrate", "sub_divisional_magistrate"),
                    new ApprovalAuthority(9L, "District Magistrate and Collector", "district_magistrate_collector"),
                    new ApprovalAuthority(10L, "Land Revenue Commissioner", "land_revenue_commissioner")
            );

            approvalAuthorityRepository.saveAll(authorities);
            System.out.println("10 approval authorities inserted into tbl_approval_authority.");
        } else {
            System.out.println("tbl_approval_authority already contains data. No action needed.");
        }
    }
}
