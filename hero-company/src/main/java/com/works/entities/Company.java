package com.works.entities;//package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long companyId;
    @NotBlank(message = "enter the companyName information")
    @Length(message = "the length of the text should be at least 2, at most 50", min = 2, max = 50)
    private String companyName;

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
    @NotBlank(message = "enter the password information")
    @Length(message = "the length of the text should be at least 2, at most 50", min = 2, max = 50)
    private String password;
    private boolean enabled;
    private boolean tokenExpired;
}
