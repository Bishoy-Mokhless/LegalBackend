package com.kazyonplus.contracts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ContractSummary {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("store_code")
    private Long storeCode;

    @JsonProperty("governorate")
    private String governorate;

    @JsonProperty("province")
    private String province;

    @JsonProperty("branch_name")
    private String branchName;

    @JsonProperty("type")
    private String type;

    @JsonProperty("department")
    private String department;

    @JsonProperty("status")
    private String status;
}
