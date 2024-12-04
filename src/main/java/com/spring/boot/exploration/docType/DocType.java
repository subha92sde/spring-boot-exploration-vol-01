package com.spring.boot.exploration.docType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.boot.exploration.approvalAuthority.ApprovalAuthority;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_doc_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", unique = true, nullable = false)
    private String title;

    @Column(name = "title_key", unique = true, nullable = false)
    private String titleKey;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "tbl_doc_type_approval_authority",
            joinColumns = @JoinColumn(name = "doc_type_id"),
            inverseJoinColumns = @JoinColumn(name = "approval_authority_id"))
    @JsonIgnore
    private Set<ApprovalAuthority> approvalAuthoritySet = new HashSet<>();

    public DocType(Long id, String title, String titleKey) {
        this.id = id;
        this.title = title;
        this.titleKey = titleKey;
    }
}
