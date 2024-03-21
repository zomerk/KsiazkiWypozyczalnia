package com.example.ksiazkiwypozyczalnia.repo;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;


@Entity(name = "Zapytanie o wypożycznie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReserveBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String userName;
    long bookID;
    boolean isBook;

    public ReserveBooks(String userName, long bookID, boolean isBook) {
        this.userName = userName;
        this.bookID = bookID;
        this.isBook = isBook;
    }
}
