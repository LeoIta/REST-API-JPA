package com.leoita.springBootRestApi.service;

import com.leoita.springBootRestApi.model.Course;
import com.leoita.springBootRestApi.repository.CourseRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

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
}