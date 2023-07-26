package com.mapping.service.impl;

import com.mapping.entities.Address;
import com.mapping.entities.User;
import com.mapping.exception.DuplicateEntry;
import com.mapping.exception.ResourceNotFoundException;
import com.mapping.payload.AddressDto;
import com.mapping.repositories.AddressRepository;
import com.mapping.repositories.UserRepository;
import com.mapping.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressSeviceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AddressDto saveAddress(long userId, AddressDto addressDto) {
        Optional<User> user = userRepository.findById(userId);
        Address address = addressRepository.findByUserId(userId);

        if (user.isPresent()) {
            Address address1 = mapToEntity(addressDto);
            if (address == null) {
                address1.setUser(user.get());
                Address newAddress = addressRepository.save(address1);
                return mapToDto(newAddress);
            }
            throw new DuplicateEntry("User already updated his address");

        }
        throw new ResourceNotFoundException("User not found with id : " + userId);
    }

    @Override
    public AddressDto getById(long addressId) {
        Optional<Address> address = addressRepository.findById(addressId);

        if (address.isPresent()) {
            Address address1 = address.get();
            return mapToDto(address1);
        }
        throw new ResourceNotFoundException("No user found with this Address Id : " + addressId);
    }

    @Override
    public List<AddressDto> getAll() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteAddressById(long addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id : " + addressId));
        addressRepository.delete(address);
        System.out.println("Deleted!!!!");

    }

    @Override
    public List<AddressDto> search(String q) {
        List<Address> addresses = addressRepository.findByCityContainingOrCountryContaining(q, q);
        if (addresses.isEmpty()) {
            throw new ResourceNotFoundException("No records to show");
        }
        return addresses.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public Address mapToEntity(AddressDto addressDto) {
        return modelMapper.map(addressDto, Address.class);
    }

    public AddressDto mapToDto(Address address) {
        return modelMapper.map(address, AddressDto.class);
    }
}
