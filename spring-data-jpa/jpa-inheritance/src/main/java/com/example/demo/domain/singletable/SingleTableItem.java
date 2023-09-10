package com.example.demo.domain.singletable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public abstract class SingleTableItem {

    @Id @GeneratedValue
    private long id;

    private String name;
    private int price;

    public SingleTableItem(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
