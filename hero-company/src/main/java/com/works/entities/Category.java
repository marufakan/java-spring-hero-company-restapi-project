package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer categoryId;

    @NotBlank(message = "enter the category name information")
    @Length(message = "the length of the text should be at least 2, at most 50", min = 2, max = 50)
    private String name;

//    @OneToOne(mappedBy = "category")
//    private Product product;

}
