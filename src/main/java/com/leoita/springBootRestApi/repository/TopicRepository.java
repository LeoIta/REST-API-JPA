package com.leoita.springBootRestApi.repository;

import com.leoita.springBootRestApi.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, String> {
}