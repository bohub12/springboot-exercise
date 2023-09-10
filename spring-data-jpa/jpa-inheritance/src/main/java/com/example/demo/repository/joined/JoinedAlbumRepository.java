package com.example.demo.repository.joined;

import com.example.demo.domain.joined.JoinedAlbum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoinedAlbumRepository extends JpaRepository<JoinedAlbum, Long> {
}
