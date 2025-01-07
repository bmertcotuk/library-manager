package com.bmcotuk.library_manager.service;

import com.bmcotuk.library_manager.repository.BookRepository;
import com.bmcotuk.library_manager.repository.model.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Transactional
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Transactional
    public Book updateBook(String id, Book book) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        existingBook.setAuthor(book.getAuthor());
        existingBook.setTitle(book.getTitle());
        existingBook.setIsbn(book.getIsbn());
        existingBook.setPublicationYear(book.getPublicationYear());
        existingBook.setCopiesAvailable(book.getCopiesAvailable());
        return bookRepository.save(book);
    }

    @Transactional
    public Book deleteBookById(String id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        bookRepository.deleteById(id);
        return book;
    }

    public void incrementCopiesAvailable(String bookId) {
        bookRepository.incrementCopiesAvailable(bookId);
    }

    public void decrementCopiesAvailable(String bookId) {
        bookRepository.decrementCopiesAvailable(bookId);
    }

    public List<Book> searchByTitleOrAuthor(String keyword, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return bookRepository.searchByTitleOrAuthor(keyword, pageRequest);
    }
}
