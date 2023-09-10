package com.example.demo.domain;

import jakarta.persistence.Entity;

@Entity
public class Album extends Item {

    private String artist;
}
