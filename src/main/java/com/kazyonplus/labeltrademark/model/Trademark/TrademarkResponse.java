package com.kazyonplus.labeltrademark.model.Trademark;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TrademarkResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("code")
    private String code;

    @JsonProperty("registration_date")
    private Date registrationDate;
    
    @JsonProperty("expiry_date")
    private Date expiryDate;

    @JsonProperty("type")
    private String type;

    @JsonProperty("status")
    private String status;

    @JsonProperty("logo")
    private String logo;

    @JsonProperty("has_attachment")
    private Boolean hasAttachment;
}
