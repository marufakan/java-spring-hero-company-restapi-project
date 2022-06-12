package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
public class JWTCustomer extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "enter the firstName information")
    @Length(message = "the length of the text should be at least 2, at most 50", min = 2, max = 50)
    private String firstName;
    @NotBlank(message = "enter the lastName information")
    @Length(message = "the length of the text should be at least 2, at most 50", min = 2, max = 50)
    private String lastName;
    @NotBlank(message = "enter the email information")
    @Length(message = "the length of the text should be at least 2, at most 60", min = 2, max = 60)
    @Email(message = "please login in email format")
    private String email;
    //    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",message="length must be 3")
    private String password;
    private boolean enabled;
    private boolean tokenExpired;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "role_id",referencedColumnName = "id")
    private Role roles;
}
