package com.kazyonplus.contracts.model;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table
@Data
public class Contract {
    //Will check with business before applying nullable
    @Id
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "store_code", unique = true)
    private Long storeCode;

    @Column(name = "sap_code")
    private String sapCode;

    @Column(name = "governorate")
    private String governorate;

    @Column(name = "province")
    private String province;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "valid_through_date")
    private Date validThroughDate;

    @Column(name = "renewal_date")
    private Date renewalDate;

    @Column(name = "opening_date")
    private Date openingDate;

    @Column(name = "receiving_date")
    private Date receivingDate;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "status")
    private String status;

    @CreatedDate
    @Column(name = "created_time", updatable = false)
    private LocalDateTime createdTime;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private Long createdBy;

    @LastModifiedDate
    @Column(name = "modified_time")
    private LocalDateTime modifiedTime;

    @LastModifiedBy
    @Column(name = "modified_by")
    private Long modifiedBy;

    @Column(name = "has_attachment")
    private Boolean hasAttachment = false;
}
