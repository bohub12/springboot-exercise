package com.example.demo.domain.joined;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("M")
public class JoinedMovie extends JoinedItem {
    private String director;
}
