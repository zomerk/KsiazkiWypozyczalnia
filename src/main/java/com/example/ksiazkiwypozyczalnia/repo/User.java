package com.example.ksiazkiwypozyczalnia.repo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "User Table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany
    List<Library> borrowedBooks = new ArrayList<>();

    public User(String username, String encode, String role) {
        this.username = username;
        this.password = encode;
        this.role = role;
    }
}
