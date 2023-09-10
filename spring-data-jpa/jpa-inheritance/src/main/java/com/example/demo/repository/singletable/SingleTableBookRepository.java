package com.example.demo.repository.singletable;

import com.example.demo.domain.singletable.SingleTableBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SingleTableBookRepository extends JpaRepository<SingleTableBook, Long> {
}
