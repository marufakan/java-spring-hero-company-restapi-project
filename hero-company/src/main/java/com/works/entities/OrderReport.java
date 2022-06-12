package com.works.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OrderReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String created_by;
    private int total;

}

