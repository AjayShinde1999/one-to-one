package com.mapping.controller;

import com.mapping.payload.UserDto;
import com.mapping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/save")
    public UserDto saveUser(@RequestBody UserDto userDto){
       return userService.createUser(userDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<UserDto> getAllUsers(){
        return userService.getAllUser();
    }

    // http://localhost:8080/api/user/search?q=ajay
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/search")
    public List<UserDto> search(@RequestParam(required = false) String q){
        return userService.search(q);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") long id){
        return userService.getUserById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable("id") long id){
        userService.deleteUserById(id);
        return "Deleted Successfully";
    }
}
