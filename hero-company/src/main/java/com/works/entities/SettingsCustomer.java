package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Data
public class SettingsCustomer {
    @Id
    private Long id;

//    @NotBlank(message = "enter the firstName information")
//    @Length(message = "the length of the text should be at least 2, at most 60", min = 2, max = 50)
    private String firstName;
//    @NotBlank(message = "enter the lastName information")
//    @Length(message = "the length of the text should be at least 2, at most 60", min = 2, max = 50)
    private String lastName;
//    @NotBlank(message = "enter the email information")
//    @Length(message = "the length of the text should be at least 2, at most 60", min = 2, max = 60)
//    @Email(message = "please login in email format")
    private String email;
//    @NotBlank(message = "enter the phone information")
//    @Length(message = "the length of the text should be at least 2, at most 60", min = 2, max = 50)
    private String phone;

}
