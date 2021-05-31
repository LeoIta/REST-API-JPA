package com.leoita.springBootRestApi.service;

import com.leoita.springBootRestApi.model.Address;
import com.leoita.springBootRestApi.repository.AddressRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public List<Address> getAddresses() {
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


}
