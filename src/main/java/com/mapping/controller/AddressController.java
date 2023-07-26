package com.mapping.controller;

import com.mapping.payload.AddressDto;
import com.mapping.payload.ErrorResponse;
import com.mapping.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    // http://localhost:8080/api/address/save
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/save/{userId}")
    public AddressDto saveOneAddress(@PathVariable("userId") long userId, @RequestBody AddressDto addressDto) {
        return addressService.saveAddress(userId, addressDto);
    }

    // http://localhost:8080/api/address/1
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public AddressDto getAddressById(@PathVariable("id") long id) {
        return addressService.getById(id);
    }

    // http://localhost:8080/api/address
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<AddressDto> getAll() {
        return addressService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/search")
    public List<AddressDto> search(@RequestParam(required = false) String q){

//        if(q.matches("\\d+")){
//            long id = Long.parseLong(q);
//            addressService.getById(id);
//        }
        return addressService.search(q);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ErrorResponse> deleteById(@PathVariable("id") long id){
        addressService.deleteAddressById(id);
        ErrorResponse response = new ErrorResponse();
        response.setMessage("Deleted Successfully");
        response.setSuccess(true);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
