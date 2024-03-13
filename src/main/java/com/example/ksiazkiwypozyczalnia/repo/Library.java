package com.example.ksiazkiwypozyczalnia.repo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "Collection of books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Library {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @OneToMany
    List<Books> booksList;

    @OneToMany
    List<Articles> articlesList;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany
    List<User> user;
}
