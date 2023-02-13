package com.kazyonplus.warehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
@Data
public class WarehouseResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("address")
    private String address;

    @JsonProperty("status")
    private String status;

    @JsonProperty("has_contract_attachment")
    private Boolean hasContractAttachment;

    @JsonProperty("has_external_approval_attachment")
    private Boolean hasExternalApprovalAttachment;

    @JsonProperty("has_license_attachment")
    private Boolean hasLicenseAttachment;
}
