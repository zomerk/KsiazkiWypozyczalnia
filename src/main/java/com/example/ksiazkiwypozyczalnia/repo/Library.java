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
    @NotNull
    String title;
    @NotNull
    String author;
    @Min(value = 0)
    @Max(value = 2024)
    int year;
    @Min(value = 0)
    int count;
    @Min(value = 0)
    int pages;
    @NotNull
    String type;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany
    List<User> user;
}
