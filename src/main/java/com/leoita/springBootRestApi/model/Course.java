package com.leoita.springBootRestApi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="course")
public class Course extends BaseEntity {
    private String courseName;

    @ManyToMany(mappedBy = "courses")
    @JsonIgnoreProperties("course")
    private Set<Topic> topics;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("course")
    private Set<Student> students;
}