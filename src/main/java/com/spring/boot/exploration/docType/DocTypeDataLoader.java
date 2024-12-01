package com.spring.boot.exploration.docType;

import com.spring.boot.exploration.util.DatabaseUtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Order(1)
@RequiredArgsConstructor
public class DocTypeDataLoader implements CommandLineRunner {
    private final DocTypeRepository docTypeRepository;
    private final DatabaseUtilityService<DocType> databaseUtilityService;

    @Override
    public void run(String... args) {
        if (databaseUtilityService.isTableEmpty(DocType.class)) {
            List<DocType> docTypes = Arrays.asList(
                    new DocType(1L, "Government Land Lease Deed", "government_land_lease_deed"),
                    new DocType(2L, "Land Acquisition Order", "land_acquisition_order"),
                    new DocType(3L, "Land Conversion Certificate", "land_conversion_certificate"),
                    new DocType(4L, "Land Title Deed", "land_title_deed"),
                    new DocType(5L, "Development Permission", "development_permission"),
                    new DocType(6L, "Large Infrastructure Project Approval", "large_infrastructure_project_approval"),
                    new DocType(7L, "Forest Land Diversion Approval", "forest_land_diversion_approval"),
                    new DocType(8L, "Mining Lease Agreement", "mining_lease_agreement"),
                    new DocType(9L, "Eviction and Resettlement Order", "eviction_and_resettlement_order"),
                    new DocType(10L, "Unauthorized Land Use Regularization", "unauthorized_land_use_regularization"),
                    new DocType(11L, "Land Subdivision Approval", "land_subdivision_approval"),
                    new DocType(12L, "Property Tax Clearance Certificate", "property_tax_clearance_certificate"),
                    new DocType(13L, "Building Plan Approval", "building_plan_approval"),
                    new DocType(14L, "Environmental Clearance Certificate", "environmental_clearance_certificate"),
                    new DocType(15L, "Zoning Certificate", "zoning_certificate"),
                    new DocType(16L, "Land Transfer Deed", "land_transfer_deed"),
                    new DocType(17L, "Land Allotment Certificate", "land_allotment_certificate"),
                    new DocType(18L, "Right of Way Authorization", "right_of_way_authorization"),
                    new DocType(19L, "Boundary Demarcation Certificate", "boundary_demarcation_certificate"),
                    new DocType(20L, "Renewal of Land Lease Agreement", "renewal_of_land_lease_agreement")
            );

            docTypeRepository.saveAll(docTypes);
            System.out.println("20 document types inserted into tbl_doc_type.");
        } else {
            System.out.println("tbl_doc_type already contains data. No action needed.");
        }
    }
}
