package com.works.entities;//package com.works.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int total;
    private String uuid;

}
