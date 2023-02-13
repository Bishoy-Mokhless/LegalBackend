package com.kazyonplus.licenses.model.Branch;

import com.kazyonplus.licenses.model.License.ExternalApproval;
import com.kazyonplus.licenses.model.License.License;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table
@Data
public class Branch {
    @Id
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "store_code", unique = true)
    private String storeCode;

    @Column(name = "sap_code")
    private String sapCode;

    @Column(name = "address")
    private String address;

    @Column(name = "governorate")
    private String governorate;

    @Column(name = "province")
    private String province;

    @Column(name = "status")
    private String status;

    @ElementCollection
    @CollectionTable(name = "external_approvals",
            joinColumns = @JoinColumn(name = "external_approval_id"))
    private List<ExternalApproval> externalApprovals;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "working_id", referencedColumnName = "id")
    private License workingLicense;

    @Column(name = "has_working_license")
    private Boolean hasWorkingLicense;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "advertisement_id", referencedColumnName = "id")
    private License advertisementLicense;

    @Column(name = "has_advertisement_license")
    private Boolean hasAdvertisementLicense;
}
