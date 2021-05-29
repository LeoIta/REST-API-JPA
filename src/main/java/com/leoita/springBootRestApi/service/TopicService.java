package com.leoita.springBootRestApi.service;

import com.leoita.springBootRestApi.model.Topic;
import com.leoita.springBootRestApi.repository.TopicRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;

    public List<Topic> getTopics() {
        return topicRepository.findAll();
    }

    public Topic getTopic(String id) {
        return topicRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("no such topic"));
    }

    public Topic saveTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    public void deleteTopic(String id) {
        topicRepository.deleteById(id);
    }
}