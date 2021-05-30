package com.leoita.springBootRestApi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class Address extends BaseEntity {

    @OneToOne(mappedBy = "address")
    @JsonIgnoreProperties("address")
    private Student student;

    private String City;
    private String Country;
    private String mail;
}
