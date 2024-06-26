package com.example.ksiazkiwypozyczalnia.repo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Table(name = "wypożyczenie książki")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WypozyczenieKsiazki {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date rentDate;

    private Date returnDate;

    private String  allowed = "null";

    private boolean returnRequest;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User uzytkownik;

    @ManyToOne
    @JoinColumn(name = "książkaID")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Ksiazka ksiazka;



}
