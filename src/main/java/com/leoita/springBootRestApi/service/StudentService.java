package com.leoita.springBootRestApi.service;

import com.leoita.springBootRestApi.model.Address;
import com.leoita.springBootRestApi.model.Student;
import com.leoita.springBootRestApi.repository.AddressRepository;
import com.leoita.springBootRestApi.repository.CourseRepository;
import com.leoita.springBootRestApi.repository.StudentRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final AddressRepository addressRepository;


    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(String id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("no such student"));
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }

    public Student assignAddressToStudent(String studentId, String addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("no such address"));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("no such student"));
        student.setAddress(address);
        address.setStudent(student);
        addressRepository.save(address);
        return studentRepository.save(student);
    }
}