package com.kazyonplus.licenses.model.Branch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BranchSummary {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("store_code")
    private Long storeCode;

    @JsonProperty("sap_code")
    private Long sapCode;

    @JsonProperty("address")
    private String address;

    @JsonProperty("governorate")
    private String governorate;

    @JsonProperty("province")
    private String province;

    @JsonProperty("status")
    private String status;
}
