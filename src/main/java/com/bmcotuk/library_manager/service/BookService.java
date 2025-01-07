package com.bmcotuk.library_manager.service;

import com.bmcotuk.library_manager.repository.model.Book;

import java.util.List;

public interface BookService {

    Book saveBook(Book book);

    Book getBookById(String id);

    Book updateBook(String id, Book book);

    Book deleteBookById(String id);

    void incrementCopiesAvailable(String bookId);

    void decrementCopiesAvailable(String bookId);

    List<Book> searchByTitleOrAuthor(String keyword, int page, int size);
}
