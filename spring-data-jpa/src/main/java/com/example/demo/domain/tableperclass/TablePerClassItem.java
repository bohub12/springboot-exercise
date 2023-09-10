package com.example.demo.domain.tableperclass;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class TablePerClassItem {

    @Id @GeneratedValue
    private long id;

    private String name;
    private int price;
}
