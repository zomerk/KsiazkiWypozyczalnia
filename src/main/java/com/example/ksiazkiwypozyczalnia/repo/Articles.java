package com.example.ksiazkiwypozyczalnia.repo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name = "Article Table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Articles{
    @Id
    int id;
    String title;
    String author;
    String journal;
    int journalNumber;
    String language;
    String type;
    @ManyToOne
    User user;



}
