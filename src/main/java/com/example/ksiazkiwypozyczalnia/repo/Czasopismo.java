package com.example.ksiazkiwypozyczalnia.repo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "czasopismo")
public class Czasopismo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isTaken;

    private int journalNumber;

    private String author;

    private String journal;

    private String language;

    private String title;

    private String type;

}
