//package com.works.entities;
//
//import lombok.Data;
//
//import javax.persistence.*;
//import javax.validation.constraints.Email;
//import java.util.List;
//
//@Entity
//@Data
//public class JWTUser extends Base{
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    private String firstName;
//    private String lastName;
//    @Email(message = "email giriniz")
//    private String email;
//    private String password;
//    private boolean enabled;
//    private boolean tokenExpired;
//
//    @ManyToMany
//    @JoinTable( name = "jwtuser_role",
//            joinColumns = @JoinColumn( name = "jwtuser_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn( name = "role_id", referencedColumnName = "id")
//    )
//
//    private List<Role> roles;
//}
