package com.bmcotuk.library_manager.repository;

import com.bmcotuk.library_manager.repository.model.Book;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {


    @Query("SELECT b FROM Book b " +
            "WHERE :keyword IS NULL OR b.title like %:keyword% OR b.author like %:keyword%")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Book> searchByTitleOrAuthor(String keyword, Pageable pageable);

    @Modifying
    @Query("UPDATE Book b SET b.copiesAvailable = b.copiesAvailable - 1 WHERE b.id = :bookId")
    void decrementCopiesAvailable(String bookId);

    @Modifying
    @Query("UPDATE Book b SET b.copiesAvailable = b.copiesAvailable + 1 WHERE b.id = :bookId")
    void incrementCopiesAvailable(String bookId);
}
