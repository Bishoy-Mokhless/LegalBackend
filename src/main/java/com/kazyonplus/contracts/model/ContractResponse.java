package com.kazyonplus.contracts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ContractResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("store_code")
    private Long storeCode;

    @JsonProperty("sap_code")
    private String sapCode;

    @JsonProperty("governorate")
    private String governorate;

    @JsonProperty("province")
    private String province;

    @JsonProperty("end_date")
    private Date endDate;

    @JsonProperty("valid_through")
    private Date validThroughDate;

    @JsonProperty("renewal_date")
    private Date renewalDate;

    @JsonProperty("opening_date")
    private Date openingDate;

    @JsonProperty("receiving_date")
    private Date receivingDate;

    @JsonProperty("branch_name")
    private String branchName;

    @JsonProperty("branch_address")
    private String branchAddress;

    @JsonProperty("status")
    private String status;

    @JsonProperty("has_attachment")
    private Boolean hasAttachment;
    //Will not include attachments
    //Might include modification and creation info after clarifying with business
}
