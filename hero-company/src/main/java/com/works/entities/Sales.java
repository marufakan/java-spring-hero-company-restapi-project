package com.works.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Sales extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Long customerId;

}
