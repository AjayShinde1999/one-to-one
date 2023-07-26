package com.mapping.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @OneToOne(mappedBy = "user",cascade = {CascadeType.ALL, CascadeType.REMOVE},orphanRemoval = true)
    private Address address;
}
