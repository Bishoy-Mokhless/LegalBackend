package com.kazyonplus.warehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
@Data
public class WarehouseRequest {
    @JsonProperty("address")
    private String address;

    @JsonProperty("status")
    private String status;
}
