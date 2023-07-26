package com.mapping.service;
import com.mapping.payload.UserDto;
import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    List<UserDto> getAllUser();

    UserDto getUserById(long id);

    void deleteUserById(long id);

    List<UserDto> search(String search);
}
