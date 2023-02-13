package com.kazyonplus.licenses.model.Branch;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kazyonplus.licenses.model.License.ExternalApproval;
import com.kazyonplus.licenses.model.License.License;
import lombok.Data;

import java.util.List;

@Data
public class BranchResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("store_code")
    private String storeCode;

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

    @JsonProperty("external_approvals")
    private List<ExternalApproval> externalApprovals;

    @JsonProperty("working_license")
    private License workingLicense;

    @JsonProperty("has_working_license")
    private Boolean hasWorkingLicense;

    @JsonProperty("advertisement_license")
    private License advertisementLicense;

    @JsonProperty("has_advertisement_license")
    private Boolean hasAdvertisementLicense;
}
