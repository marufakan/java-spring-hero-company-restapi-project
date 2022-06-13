package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class JWTLogin {
    @NotBlank(message = "enter the email information")
    @Length(message = "the length of the text should be at least 2, at most 60", min = 2, max = 60)
    @Email(message = "please login in email format")
    private  String username;
    private  String password;
}
