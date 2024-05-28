package com.nikhil.bookVerse.repository;

import com.nikhil.bookVerse.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByBookId(@Param("book_id") Long bookId, Pageable pageable);

    Review findByUserEmailAndBookId(String userEmail, Long bookId);

    @Modifying
    @Query("delete from Review r where r.bookId = :bookId")
    void deleteAllByBookId(@Param("bookId") Long bookId);
}
