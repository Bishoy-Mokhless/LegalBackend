package com.kazyonplus.warehouse.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class WarehouseLicense {
    @Id
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private String status;

    @Column(name = "has_contract_attachment")
    private Boolean hasContractAttachment;

    @Column(name = "has_external_approval_attachment")
    private Boolean hasExternalApprovalAttachment;

    @Column(name = "has_license_attachment")
    private Boolean hasLicenseAttachment;
}
