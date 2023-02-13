package com.kazyonplus.labeltrademark.model.Label;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class LabelResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("code")
    private String code;

    @JsonFormat(pattern="yyyy/MM/dd")
    @JsonProperty("registration_date")
    private Date registrationDate;

    @JsonFormat(pattern="yyyy/MM/dd")
    @JsonProperty("expiry_date")
    private Date expiryDate;

    @JsonProperty("type")
    private String type;

    @JsonProperty("status")
    private String status;

    @JsonProperty("logo")
    private byte[] logo;

    @JsonProperty("has_attachment")
    private Boolean hasAttachment;
}
