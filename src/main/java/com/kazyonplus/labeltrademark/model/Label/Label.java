package com.kazyonplus.labeltrademark.model.Label;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.*;

@Data
@Table
@Entity
public class Label {
    @Id
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "registration_date")
    private Date registrationDate;

    @Column(name = "expiry_date")
    private Date expiryDate;

    @Column(name = "type", nullable = true)
    private String type;

    @Column(name = "status")
    private String status;

    @Lob
    @Column (name = "logo")
    private byte[] logo;

    @Column(name = "has_attachment")
    private Boolean hasAttachment = false;

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
        Calendar c = getInstance();
        c.setTime(registrationDate);
        c.add(YEAR, 10);
        this.expiryDate = c.getTime();
        Calendar c1 = getInstance(), c2 = getInstance();
        status = determineStatus(expiryDate);
    }
    private String determineStatus(Date expiryDate){
        Calendar c1 = getInstance(), c2 = getInstance();
        c2.add(MONTH, 2);
        if(expiryDate.after(c1.getTime()) && expiryDate.before(c2.getTime())) {
            return "Renewal";
        } else if(expiryDate.before(c1.getTime())){
            return "Expired";
        }
        return "Valid";
    }
    public String getStatus(){
        this.status = determineStatus(expiryDate);
        return status;
    }
}
