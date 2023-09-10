package com.example.demo.domain.joined;

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
public class JoinedAlbum extends JoinedItem {

    private String artist;

    public JoinedAlbum(String name, int price, String artist) {
        super(name, price);
        this.artist = artist;
    }
}
