package com.kazyonplus.labeltrademark.model.Trademark;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TrademarkRequest {
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
    private String logo;
}
