package com.kazyonplus.licenses.model.License;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class LicenseResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    String name;

    @JsonProperty("status")
    String status;

    @JsonFormat(pattern="yyyy/MM/dd")
    @JsonProperty("renewal_date")
    Date renewalDate;
}
