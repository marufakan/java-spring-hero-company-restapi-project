package com.works.entities;//package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    private Long customerId;
    @Positive(message = "total should be positive")
    private int total;
    private String date;//date nasıl kullanılacak ?
    private String uuid;
}
