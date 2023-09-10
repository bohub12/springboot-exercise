package com.example.demo.domain;

import jakarta.persistence.Entity;

@Entity
public class Movie extends Item {
    private String director;
}
