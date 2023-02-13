package com.kazyonplus.licenses.model.License;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Embeddable;
import java.util.Date;

@Data
@Embeddable
public class ExternalApproval {
    @JsonProperty("name")
    String name;

    @JsonFormat(pattern="yyyy/MM/dd")
    @JsonProperty("registration_date")
    Date registrationDate;

    @JsonFormat(pattern="yyyy/MM/dd")
    @JsonProperty("renewal_date")
    Date renewalDate;
}
