package com.example.ksiazkiwypozyczalnia.repo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "czasopismo", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<WypozyczenieCzasopisma> wypozyczeniaCzasopisma = new ArrayList<>();

}
