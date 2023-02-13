package com.kazyonplus.contracts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class ContractStatusCount {
    @JsonProperty("valid")
    private Long Valid;

    @JsonProperty("renewal")
    private Long Renewal;

    @JsonProperty("annulled")
    private Long Annulled;
}
