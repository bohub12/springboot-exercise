package com.example.demo.domain.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
@DiscriminatorValue("A")
public class SingleTableAlbum extends SingleTableItem {

    private String artist;

    public SingleTableAlbum(String name, int price, String artist) {
        super(name, price);
        this.artist = artist;
    }
}
