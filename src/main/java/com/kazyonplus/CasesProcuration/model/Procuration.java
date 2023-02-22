package com.kazyonplus.CasesProcuration.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kazyonplus.files.model.Doc;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Procurations")
public class Procuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String client_name;

    @OneToOne
    private Doc attachment;

    private int office_procuration_number;
    private String office;
    private int procuration_number;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date year;
    private boolean hasAttachment;

    public boolean isHasAttachment() {
        return hasAttachment;
    }

    public void setHasAttachment(boolean hasAttachment) {
        this.hasAttachment = hasAttachment;
    }
    //test
    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public int getOffice_procuration_number() {
        return office_procuration_number;
    }

    public void setOffice_procuration_number(int office_procuration_number) {this.office_procuration_number = office_procuration_number;}

    public int getProcuration_number() {
        return procuration_number;
    }

    public void setProcuration_number(int procuration_number) {
        this.procuration_number = procuration_number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public Doc getAttachment() {
        return attachment;
    }

    public void setAttachment(Doc attachment) {
        this.attachment = attachment;
    }
}
