package com.example.demo.domain.tableperclass;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class TablePerClassItem {

    @Id @GeneratedValue
    private long id;

    private String name;
    private int price;

    public TablePerClassItem(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
