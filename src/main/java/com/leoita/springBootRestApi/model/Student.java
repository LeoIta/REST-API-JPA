package com.leoita.springBootRestApi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Student extends BaseEntity {
    private String studentName;

    @ManyToOne
    @JoinColumn(name="course_id")
    @JsonIgnoreProperties("students")
    private Course course;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @JsonIgnoreProperties("student")
    private Address address;
}