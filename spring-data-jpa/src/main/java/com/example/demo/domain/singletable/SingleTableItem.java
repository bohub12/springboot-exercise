package com.example.demo.domain.singletable;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public abstract class SingleTableItem {

    @Id @GeneratedValue
    private long id;

    private String name;
    private int price;
}
