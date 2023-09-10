package com.example.demo.domain.tableperclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
public class TablePerClassBook extends TablePerClassItem {

    private String author;
}
