package com.leoita.springBootRestApi.controller;

import com.leoita.springBootRestApi.model.Address;
import com.leoita.springBootRestApi.service.AddressService;

import lombok.RequiredArgsConstructor;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping
    public List<Address> getAddresses() {
        return addressService.getAddresses();
    }

    @GetMapping("/{address-id}")
    public Address getAddress(@PathVariable("address-id") String id) {
        return addressService.getAddress(id);
    }

    @PostMapping()
    public Address newAddress(@RequestBody Address address) {
        return addressService.saveAddress(address);
    }

    @DeleteMapping("/{address-id}")
    public String deleteAddress(@PathVariable("address-id") String Id) {
        addressService.deleteAddress(Id);
        return "address with id " + Id + " has been deleted";
    }

    @PatchMapping("/{address-id}")
    public Address updateAddressParameter(@PathVariable("address-id") String Id,
                                          @RequestBody Map<String, Object> fields) {
        Address address = addressService.getAddress(Id);
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Address.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, address, value);
            }
        });
        return addressService.saveAddress(address);
    }
}