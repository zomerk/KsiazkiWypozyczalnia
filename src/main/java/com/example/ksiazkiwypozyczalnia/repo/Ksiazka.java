package com.example.ksiazkiwypozyczalnia.repo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "ksiazka", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<WypozyczenieKsiazki> wypozyczeniaKsiazek = new ArrayList<>();
}
