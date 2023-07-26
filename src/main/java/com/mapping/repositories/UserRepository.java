package com.mapping.repositories;

import com.mapping.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findByNameContainingOrEmailContaining(String name,String email);

    User findByEmail(String email);
}
