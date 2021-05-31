package com.leoita.springBootRestApi.controller;

import com.leoita.springBootRestApi.model.Student;
import com.leoita.springBootRestApi.service.AddressService;
import com.leoita.springBootRestApi.service.CourseService;
import com.leoita.springBootRestApi.service.StudentService;

import lombok.RequiredArgsConstructor;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final CourseService courseService;
    private final AddressService addressService;

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/{student-id}")
    public Student getStudent(@PathVariable("student-id") String id) {
        return studentService.getStudent(id);
    }

    @PostMapping()
    public Student newStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PutMapping()
    public Student updateStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PatchMapping("/{student-id}")
    public Student updateStudentParameter(@PathVariable("student-id") String Id,
                                          @RequestBody Map<String, Object> fields) {
        Student student = studentService.getStudent(Id);
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Student.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, student, value);
            }
        });
        return studentService.saveStudent(student);
    }

    @DeleteMapping("/{student-id}")
    public String deleteStudent(@PathVariable("student-id") String Id) {
        studentService.deleteStudent(Id);
        return "student with id " + Id + " has been deleted";
    }

    @PatchMapping("/{student-id}/addresses/{address-id}")
    public Student assignAddressToStudent(@PathVariable("address-id") String addressId,
                                          @PathVariable("student-id") String studentId) {
        return studentService.assignAddressToStudent(studentId, addressId);
    }
}
