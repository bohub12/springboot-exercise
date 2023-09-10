package com.example.demo.controller.dto.joined;

import com.example.demo.domain.joined.JoinedAlbum;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetJoinedAlbumDto {
    private long id;
    private String name;
    private int price;
    private String artist;

    public GetJoinedAlbumDto(JoinedAlbum entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.artist = entity.getArtist();
    }
}
