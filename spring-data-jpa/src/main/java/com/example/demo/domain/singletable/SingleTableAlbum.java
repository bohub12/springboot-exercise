package com.example.demo.domain.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class SingleTableAlbum extends SingleTableItem {

    private String artist;
}
