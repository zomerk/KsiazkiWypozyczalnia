package com.example.ksiazkiwypozyczalnia.repo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "User Table")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @NotNull
    String username;
    @NotNull
    String password;
    @JsonIgnore
    String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    @JsonProperty(access = JsonProperty.Access.AUTO)
    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY,mappedBy = "user")
    List<Books> borrowedBooks = new ArrayList<>();
//
//    @JsonProperty(access = JsonProperty.Access.AUTO)
//    @JoinColumn(name = "Library_id")
//    @OneToMany()
//    List<Articles> borrowedArticles = new ArrayList<>();

//    @OneToMany
//    List<Books> booksWantedList =  new ArrayList<>();
//    @OneToMany
//    List<Articles> articlesWantedList = new ArrayList<>();

}
