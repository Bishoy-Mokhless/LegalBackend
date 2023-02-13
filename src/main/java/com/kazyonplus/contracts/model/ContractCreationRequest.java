package com.kazyonplus.contracts.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ContractCreationRequest {
    @JsonProperty("store_code")
    private Long storeCode;

    @JsonProperty("sap_code")
    private String sapCode;

    @JsonProperty("governorate")
    private String governorate;

    @JsonProperty("province")
    private String province;

    @JsonFormat(pattern="yyyy/MM/dd")
    @JsonProperty("end_date")
    private Date endDate;

    @JsonFormat(pattern="yyyy/MM/dd")
    @JsonProperty("valid_through")
    private Date validThroughDate;

    @JsonFormat(pattern="yyyy/MM/dd")
    @JsonProperty("renewal_date")
    private Date renewalDate;

    @JsonFormat(pattern="yyyy/MM/dd")
    @JsonProperty("opening_date")
    private Date openingDate;

    @JsonFormat(pattern="yyyy/MM/dd")
    @JsonProperty("receiving_date")
    private Date receivingDate;

    @JsonProperty("branch_name")
    private String branchName;

    @JsonProperty("branch_address")
    private String branchAddress;

    @JsonProperty("status")
    private String status;

    @JsonProperty("has_attachment")
    private Boolean has_attachment;
}
