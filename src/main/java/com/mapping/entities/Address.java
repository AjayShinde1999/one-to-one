package com.mapping.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city",nullable = false)
    private String city;

    @Column(name = "country",nullable = false)
    private String country;

    @OneToOne
    @JoinColumn(name = "user_id",unique = true)
    private User user;
}
