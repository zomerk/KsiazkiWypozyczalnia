package com.example.ksiazkiwypozyczalnia.repo;

import jakarta.persistence.*;
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

    private boolean allowed;

    private boolean returnRequest;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User uzytkownik;


    @ManyToOne
    @JoinColumn(name = "czasopismoID")
    private Czasopismo czasopismo;

}