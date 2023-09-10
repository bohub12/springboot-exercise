package com.example.demo.domain.joined;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("B")
public class JoinedBook extends JoinedItem {

    private String author;
}
