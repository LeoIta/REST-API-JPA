package com.leoita.springBootRestApi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="topic")
public class Topic extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private TopicName topicName;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Topic_Course",
            joinColumns = {@JoinColumn(name = "topic_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")}
    )
    @JsonIgnoreProperties("topics")
    private Set<Course> courses;
}