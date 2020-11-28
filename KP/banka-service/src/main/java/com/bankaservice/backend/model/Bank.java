package com.bankaservice.backend.model;

import javax.persistence.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Set;

@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column
    private String address;

    @Column
    private String name;

    @Column
    private ArrayList<Long> clients;



}
