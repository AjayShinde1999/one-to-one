package com.mapping.payload;

import lombok.Data;

@Data
public class AddressDto {

    private Long id;

    private String city;

    private String country;

    private UserDto user;

}
