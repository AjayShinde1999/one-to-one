package com.mapping.service.impl;

import com.mapping.entities.User;
import com.mapping.exception.DuplicateEntry;
import com.mapping.exception.ResourceNotFoundException;
import com.mapping.payload.UserDto;
import com.mapping.repositories.UserRepository;
import com.mapping.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new DuplicateEntry("Email " + userDto.getEmail() + " already registered");
        }
        User user = mapToEntity(userDto);
        User newSave = userRepository.save(user);
        return mapToDto(newSave);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> mapToDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(long id) {
//        User user = userRepository.findById(id).orElseThrow(
//                () -> new ResourceNotFoundException("User not found with id : " + id)
//        );
//        return mapToDto(user);

        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User user1 = user.get();
            return mapToDto(user1);
        }
        throw new ResourceNotFoundException("User not found with id : " + id);
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> search(String search) {
        List<User> users = userRepository.findByNameContainingOrEmailContaining(search, search);
        if (users.isEmpty()) {
            return null;
        }
        return users.stream().map(user -> mapToDto(user)).collect(Collectors.toList());
    }

    public User mapToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public UserDto mapToDto(User user) {
        return modelMapper.map(user, UserDto.class);
//        AddressDto addressDto = null;
//        if (user.getAddress() != null) {
//            addressDto = modelMapper.map(user.getAddress(), AddressDto.class);
//        }
//        userDto.setAddress(addressDto);
//        return userDto;
    }

}


