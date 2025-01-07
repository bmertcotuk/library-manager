package com.bmcotuk.library_manager.controller;

import com.bmcotuk.library_manager.controller.dto.BookRestRequest;
import com.bmcotuk.library_manager.repository.model.Book;
import com.bmcotuk.library_manager.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Book> saveBook(@Valid @RequestBody BookRestRequest restRequest) {
        Book book = bookService.saveBook(BookRestRequest.toEntity(restRequest));
        return ResponseEntity.ok(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBookById(
            @PathVariable String id,
            @Valid @RequestBody BookRestRequest restRequest) {
        return ResponseEntity.ok(
                bookService.updateBook(id, BookRestRequest.toEntity(restRequest)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBookById(@PathVariable String id) {
        return ResponseEntity.ok(bookService.deleteBookById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchByTitleOrAuthor(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(
                bookService.searchByTitleOrAuthor(keyword, page, size));
    }
}
