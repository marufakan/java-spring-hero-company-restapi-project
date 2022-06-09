package com.works.entities;//package com.works.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bid;

    private Long customerId;//varlığı denetlenecek
    private Long pid;//varlığı denetlenecek
    private String date;
    private int count;
    private int status;
    @NotBlank(message = "the uuid cannot be left blank")
    private String uuid;
    private int categoryId;
}

