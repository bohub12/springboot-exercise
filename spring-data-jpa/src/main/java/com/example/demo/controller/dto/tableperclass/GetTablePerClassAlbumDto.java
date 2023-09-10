package com.example.demo.controller.dto.tableperclass;

import com.example.demo.domain.tableperclass.TablePerClassAlbum;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetTablePerClassAlbumDto {

    private long id;
    private String name;
    private int price;
    private String artist;

    public GetTablePerClassAlbumDto(TablePerClassAlbum entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.artist = entity.getArtist();
    }
}
