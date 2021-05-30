package com.leoita.springBootRestApi.controller;

import com.leoita.springBootRestApi.model.Topic;
import com.leoita.springBootRestApi.service.CourseService;
import com.leoita.springBootRestApi.service.TopicService;

import lombok.RequiredArgsConstructor;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/topic")
@RequiredArgsConstructor
public class TopicController {
    private final TopicService topicService;
    private final CourseService courseService;

    @GetMapping
    public List<Topic> getTopics() {
        return topicService.getTopics();
    }

    @GetMapping("/{id}")
    public Topic getTopic(@PathVariable("id") String id) {
        return topicService.getTopic(id);
    }

    @PostMapping()
    public Topic newCourse(@RequestBody Topic topic) {
        return topicService.saveTopic(topic);
    }

    @PutMapping()
    public Topic updateCourse(@RequestBody Topic topic) {
        return topicService.saveTopic(topic);
    }

    @PatchMapping("/{id}")
    public Topic updateTopicParameter(@PathVariable("id") String Id,
                                       @RequestBody Map<String, Object> fields) {
        Topic topic = topicService.getTopic(Id);
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Topic.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, topic, value);
            }
        });
        return topicService.saveTopic(topic);
    }

    @DeleteMapping("/{id}")
    public String deleteTopic(@PathVariable("id") String Id) {
        topicService.deleteTopic(Id);
        return "topic with id " + Id + " has been deleted";
    }

    @PatchMapping("/{topic-id}/{course-id}")
    public Topic updateTopicCourse(@PathVariable("course-id") String courseId,
                                   @PathVariable("topic-id") String topicId) {
        return courseService.updateTopicCourse(topicId, courseId);
    }
}