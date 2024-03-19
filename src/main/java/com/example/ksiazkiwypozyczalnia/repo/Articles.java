package com.example.ksiazkiwypozyczalnia.repo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String title;
    String author;
    String journal;
    int journalNumber;
    String language;
    String type;
    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "user_id")
    User user;



}
