package com.example.demo.controller.dto.singletable;

import com.example.demo.domain.singletable.SingleTableAlbum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetSingleTableAlbumDto {

    private long id;
    private String name;
    private int price;
    private String artist;

    public GetSingleTableAlbumDto(SingleTableAlbum entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.artist = entity.getArtist();
    }
}
