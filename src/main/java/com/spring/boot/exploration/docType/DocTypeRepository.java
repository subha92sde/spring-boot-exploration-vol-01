package com.spring.boot.exploration.docType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocTypeRepository extends JpaRepository<DocType, Long> {
    @Query(value = "SELECT a.id, a.title, a.title_key FROM tbl_approval_authority a " +
            "JOIN tbl_doc_type_approval_authority dta ON a.id = dta.approval_authority_id " +
            "WHERE dta.doc_type_id = :docTypeId", nativeQuery = true)
    List<Object[]> findApprovalAuthoritiesRawByDocTypeId(@Param("docTypeId") Long docTypeId);

}
