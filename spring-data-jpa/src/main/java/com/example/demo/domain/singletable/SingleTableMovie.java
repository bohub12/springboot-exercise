package com.example.demo.domain.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("M")
public class SingleTableMovie extends SingleTableItem {
    private String director;
}
