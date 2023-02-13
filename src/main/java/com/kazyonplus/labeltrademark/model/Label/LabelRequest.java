package com.kazyonplus.labeltrademark.model.Label;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class LabelRequest {
    @JsonProperty("name")
    private String name;

    @JsonProperty("code")
    private String code;

    @JsonFormat(pattern="yyyy/MM/dd")
    @JsonProperty("registration_date")
    private Date registrationDate;

    @JsonProperty("type")
    private String type;

    @JsonProperty("logo")
    private byte[] logo;
}
