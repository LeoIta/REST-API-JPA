package com.leoita.springBootRestApi.controller;

import com.leoita.springBootRestApi.model.Course;
import com.leoita.springBootRestApi.service.CourseService;

import lombok.RequiredArgsConstructor;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public List<Course> getCourses() {
        return courseService.getCourses();
    }

    @GetMapping("/{course-id}")
    public Course getCourse(@PathVariable("course-id") String id) {
        return courseService.getCourse(id);
    }

    @PostMapping()
    public Course newCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @PutMapping()
    public Course updateCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @PatchMapping("/{course-id}")
    public Course updateCourseParameter(@PathVariable("course-id") String Id,
                                        @RequestBody Map<String, Object> fields) {
        Course course = courseService.getCourse(Id);
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Course.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, course, value);
            }
        });
        return courseService.saveCourse(course);
    }

    @DeleteMapping("/{course-id}")
    public String deleteCourse(@PathVariable("course-id") String Id) {
        courseService.deleteCourse(Id);
        return "course with id " + Id + " has been deleted";
    }

    @PatchMapping("/{course-id}/students/{student-id}")
    public Course addStudentToCourse(@PathVariable("course-id") String courseId,
                                     @PathVariable("student-id") String studentId) {
        return courseService.addStudentToCourse(studentId,courseId);
    }

    @PatchMapping("/{course-id}/topics/{topic-id}")
    public Course addTopicToCourse(@PathVariable("course-id") String courseId,
                                   @PathVariable("topic-id") String topicId) {
        return courseService.addTopicToCourse(courseId, topicId);
    }
}
