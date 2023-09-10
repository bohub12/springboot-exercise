package com.example.demo.domain.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("B")
public class SingleTableBook extends SingleTableItem {

    private String author;
}
