//package com.bh.workouts.bhworkoutapp.models;
//
//import lombok.*;
//
//import javax.persistence.*;
//import java.util.Collection;
//
//@Entity
//@Table(name = "role")
//@Data
//public class Role {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "role_id")
//    private Long id;
//
//    @Column(name = "role", unique = true)
//    private String role;
//
//    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "roles")
//    private Collection<User> users;
//
//
//}
