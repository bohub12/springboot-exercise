package com.example.demo.controller;

import com.example.demo.domain.joined.JoinedAlbum;
import com.example.demo.domain.singletable.SingleTableAlbum;
import com.example.demo.domain.tableperclass.TablePerClassAlbum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAlbumDto {

    private String name;
    private int price;
    private String artist;

    public SingleTableAlbum toSingleTableEntity() {
        return new SingleTableAlbum(name, price, artist);
    }

    public TablePerClassAlbum toTablePerClassEntity() {
        return new TablePerClassAlbum(name, price, artist);
    }

    public JoinedAlbum toJoinedEntity() {
        return new JoinedAlbum(name, price, artist);
    }
}
