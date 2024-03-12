package com.example.ksiazkiwypozyczalnia.Dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class LibraryDto {
    @NonNull
    String title;
    @NonNull
    String author;
    @Min(value = 1)
    @Max(value = 2024)
    int year;
    @Min(value = 0)
    int count;
    @Min(value = 1)
    int pages;
    @NonNull
    String type;
}
