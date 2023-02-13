package com.kazyonplus.CasesProcuration.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ProcurationDTO {
    private int id;
    private String client_name;
    private int office_procuration_number;
    private String office;
    private int procuration_number;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date year;
    private boolean hasAttachment;
}
