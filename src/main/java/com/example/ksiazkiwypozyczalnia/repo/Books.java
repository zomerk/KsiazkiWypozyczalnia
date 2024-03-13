package com.example.ksiazkiwypozyczalnia.repo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Books {
    @Id
    int id;
    String title;
    @NotNull
    String author;
    @Min(value = 0)
    @Max(value = 2024)
    int year;
    @Min(value = 0)
    int pages;
    @NotNull
    String type;

}