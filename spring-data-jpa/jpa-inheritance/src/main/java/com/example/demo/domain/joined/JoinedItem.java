package com.example.demo.domain.joined;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
public abstract class JoinedItem {

    @Id @GeneratedValue
    private long id;

    private String name;
    private int price;

    public JoinedItem(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
