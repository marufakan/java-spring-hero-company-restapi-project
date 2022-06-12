package com.works.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Role{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
}

