package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id @GeneratedValue
    private Long id;
    private String title;
    private String content;

    @OneToMany(mappedBy = "board")
    @JsonManagedReference
    private List<Comment> comments = new ArrayList<>();

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
