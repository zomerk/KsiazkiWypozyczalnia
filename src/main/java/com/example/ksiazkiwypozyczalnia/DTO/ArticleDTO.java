package com.example.ksiazkiwypozyczalnia.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDTO {
    @JsonIgnore
    private boolean isTaken = false;

    @Min(1)
    private int journalNumber;

    @NotEmpty(message = "author cannot be empty")
    private String author;

    @NotEmpty(message = "journal cannot be empty")
    private String journal;

    @NotEmpty(message = "language cannot be empty")
    private String language;

    @NotEmpty(message = "title cannot be empty")
    private String title;

    @NotEmpty(message = "type cannot be empty")
    private String type;

    public ArticleDTO(int journalNumber, String author, String journal, String language, String title, String type) {
        this.journalNumber = journalNumber;
        this.author = author;
        this.journal = journal;
        this.language = language;
        this.title = title;
        this.type = type;
    }
}
