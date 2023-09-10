package com.example.demo.repository.singletable;

import com.example.demo.domain.singletable.SingleTableAlbum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SingleTableAlbumRepository extends JpaRepository<SingleTableAlbum, Long> {
}
