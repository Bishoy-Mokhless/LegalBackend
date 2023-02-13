package com.kazyonplus.licenses.model.Branch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BranchRequest {
    @JsonProperty("store_code")
    private String storeCode;

    @JsonProperty("sap_code")
    private String sapCode;

    @JsonProperty("address")
    private String address;

    @JsonProperty("governorate")
    private String governorate;

    @JsonProperty("province")
    private String province;

    @JsonProperty("status")
    private String status;
}
