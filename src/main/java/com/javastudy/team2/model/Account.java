package com.javastudy.team2.model;

import lombok.*;


import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
public class Account {


    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();


    @Transient
    private String no;

    @Embedded
    private Address address;

    //@OneToMany(mappedBy = "owner")
    //private Set<Study>

}
