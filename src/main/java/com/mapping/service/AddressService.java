package com.mapping.service;

import com.mapping.entities.Address;
import com.mapping.payload.AddressDto;

import java.util.List;

public interface AddressService {

    AddressDto saveAddress(long userId, AddressDto addressDto);

    AddressDto getById(long addressId);

    List<AddressDto> getAll();

    void deleteAddressById(long addressId);

    List<AddressDto> search(String q);
}
