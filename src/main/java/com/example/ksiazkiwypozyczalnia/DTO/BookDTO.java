package com.example.ksiazkiwypozyczalnia.DTO;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Getter
@Setter

public class BookDTO {
    @JsonIgnore
    private boolean isTaken = false;
    @Min(1)
    private int pages;

    private int year;

    @NotEmpty(message = "journal cannot be empty")
    private String author;

    @NotEmpty(message = "language cannot be empty")
    private String language;

    @NotEmpty(message = "title cannot be empty")
    private String title;

    @NotEmpty(message = "type cannot be empty")
    private String type;

    public BookDTO(int pages, int year, String author, String language, String title, String type) {
        this.pages = pages;
        this.year = year;
        this.author = author;
        this.language = language;
        this.title = title;
        this.type = type;
    }
}
