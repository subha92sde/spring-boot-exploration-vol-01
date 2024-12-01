package com.spring.boot.exploration.docType;

import com.spring.boot.exploration.approvalAuthority.ApprovalAuthority;
import jakarta.persistence.*;
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

    @ManyToMany
    @JoinTable(name = "tbl_doc_type_approval_authority",
            joinColumns = @JoinColumn(name = "doc_type_id"),
            inverseJoinColumns = @JoinColumn(name = "approval_authority_id"))
    private Set<ApprovalAuthority> approvalAuthoritySet = new HashSet<>();

    public DocType(Long id, String title, String titleKey) {
        this.id = id;
        this.title = title;
        this.titleKey = titleKey;
    }
}
