package com.leoita.springBootRestApi.service;

import com.leoita.springBootRestApi.model.Course;
import com.leoita.springBootRestApi.model.Student;
import com.leoita.springBootRestApi.model.Topic;
import com.leoita.springBootRestApi.repository.CourseRepository;
import com.leoita.springBootRestApi.repository.StudentRepository;
import com.leoita.springBootRestApi.repository.TopicRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TopicRepository topicRepository;

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public Course getCourse(String id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("no such course"));
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }

    public Course addStudentToCourse(String studentId, String courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("no such course"));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("no such student"));
        student.setCourse(course);
        course.getStudents().add(student);
        return courseRepository.save(course);
    }

    public Course addTopicToCourse(String courseId, String topicId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("no such course"));
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new IllegalArgumentException("no such topic"));
        course.getTopics().add(topic);
        topic.getCourses().add(course);
        return courseRepository.save(course);
    }

}