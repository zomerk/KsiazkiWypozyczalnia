package com.example.ksiazkiwypozyczalnia.repo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "wypożyczenie czasopisma")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WypozyczenieCzasopisma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date rentDate;

    private Date returnDate;

    private String allowed = "null";

    private boolean returnRequest;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User uzytkownik;

    @ManyToOne
    @JoinColumn(name = "czasopismoID")
    private Czasopismo czasopismo;

}