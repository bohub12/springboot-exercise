package com.example.demo.domain.tableperclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
public class TablePerClassAlbum extends TablePerClassItem {

    private String artist;
}
