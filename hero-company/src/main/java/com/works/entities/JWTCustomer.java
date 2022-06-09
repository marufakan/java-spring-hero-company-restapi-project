package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Data
public class JWTCustomer extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    @Email(message = "email giriniz")
    private String email;
    private String password;
    private boolean enabled;
    private boolean tokenExpired;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "role_id",referencedColumnName = "id")
    private Role roles;
}
