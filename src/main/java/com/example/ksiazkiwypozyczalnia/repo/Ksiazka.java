package com.example.ksiazkiwypozyczalnia.repo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "książka")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ksiazka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isTaken;

    private int pages;

    private int year;

    private String author;

    private String language;

    private String title;

    private String type;

    // Getters and Setters
}
