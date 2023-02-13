package com.kazyonplus.licenses.model.License;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;


@Data
@Table
@Entity
public class License {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name")
    String name;

    @Column(name = "status")
    String status;

    @Column(name = "renewal_date")
    Date renewalDate;
}
