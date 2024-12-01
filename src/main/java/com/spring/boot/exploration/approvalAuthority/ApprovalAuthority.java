package com.spring.boot.exploration.approvalAuthority;

import com.spring.boot.exploration.docType.DocType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_approval_authority")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", unique = true, nullable = false)
    private String title;

    @Column(name = "title_key", unique = true, nullable = false)
    private String titleKey;

    @ManyToMany(mappedBy = "approvalAuthoritySet")
    private Set<DocType> docTypeSet = new HashSet<>();

    public ApprovalAuthority(Long id, String title, String titleKey) {
        this.id = id;
        this.title = title;
        this.titleKey = titleKey;
    }
}
