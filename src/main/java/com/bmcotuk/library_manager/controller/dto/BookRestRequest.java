package com.bmcotuk.library_manager.controller.dto;

import com.bmcotuk.library_manager.repository.model.Book;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookRestRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private String isbn;

    @NotNull
    @Min(value = 0, message = "Publication year must be greater than 0")
    private int publicationYear;

    @NotNull
    @Min(value = 1, message = "Copies available must be greater than 0")
    private int copiesAvailable;

    public static Book toEntity(BookRestRequest restRequest) {
        Book entity = new Book();
        entity.setTitle(restRequest.getTitle());
        entity.setAuthor(restRequest.getAuthor());
        entity.setIsbn(restRequest.getIsbn());
        entity.setPublicationYear(restRequest.getPublicationYear());
        entity.setCopiesAvailable(restRequest.getCopiesAvailable());
        return entity;
    }
}
