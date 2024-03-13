package com.example.ksiazkiwypozyczalnia.repo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Article Table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Articles {
    @Id
    int id;
}
