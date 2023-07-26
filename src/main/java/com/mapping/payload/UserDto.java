package com.mapping.payload;

import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String name;

    private String email;

    // private AddressDto address;


//    @JsonIgnore
//    public String getEmail() {
//        return email;
//    }
}
