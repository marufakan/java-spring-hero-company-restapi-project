package com.works.entities;//package com.works.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Data
public class Basket extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long pid;
    private int count;
    private int status;
    private String uuid;
    private int categoryId;
}

