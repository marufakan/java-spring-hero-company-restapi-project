package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
@Data
public class Product extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pid;

    @NotBlank(message = "enter the product name information")
    @Length(message = "the length of the text should be at least 2, at most 50", min = 2, max = 50)
    private String name;
    @Positive(message = "categoryId should be positive")
    private int categoryId;
    @NotBlank(message = "enter the product detail information")
    @Length(message = "the length of the text should be at least 2, at most 500", min = 2, max = 500)
    private String detail;
    @Positive(message = "price should be positive")
    private int price;
    @Positive(message = "price should be positive")
    private int stock;

}
