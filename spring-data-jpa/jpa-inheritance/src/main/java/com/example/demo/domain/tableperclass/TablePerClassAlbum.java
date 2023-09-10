package com.example.demo.domain.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class TablePerClassAlbum extends TablePerClassItem {

    private String artist;

    public TablePerClassAlbum(String name, int price, String artist) {
        super(name, price);
        this.artist = artist;
    }
}
