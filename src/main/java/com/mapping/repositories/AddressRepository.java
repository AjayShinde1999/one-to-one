package com.mapping.repositories;

import com.mapping.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Long> {

    Address findByUserId(long userId);

    List<Address> findByCityContainingOrCountryContaining(String city, String country);
}
