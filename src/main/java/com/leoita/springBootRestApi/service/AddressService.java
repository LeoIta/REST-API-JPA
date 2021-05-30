package com.leoita.springBootRestApi.service;

import com.leoita.springBootRestApi.model.Address;
import com.leoita.springBootRestApi.model.Student;
import com.leoita.springBootRestApi.repository.AddressRepository;

import com.leoita.springBootRestApi.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final StudentRepository studentRepository;

    public List<Address> getAddresses(){
        return addressRepository.findAll();
    }

    public Address getAddress(String id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("no such address"));
    }

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    public void deleteAddress(String id) {
        addressRepository.deleteById(id);
    }

    public Address assignStudentToAddress(String addressId, String studentId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("no such address"));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("no such student"));
        address.setStudent(student);
        return addressRepository.save(address);
    }

    public Student assignAddressToStudent(String studentId, String addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("no such address"));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("no such student"));
        student.setAddress(address);
        return studentRepository.save(student);
    }
}
