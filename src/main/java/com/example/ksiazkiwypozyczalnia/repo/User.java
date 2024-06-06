package com.example.ksiazkiwypozyczalnia.repo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

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
    @SequenceGenerator(name = "employee_seq", allocationSize = 1)
    long id;
    @NotNull
    String username;
    @NotNull
    String password;
    @JsonIgnore
    String role;
    @JsonIgnore
    @OneToMany(mappedBy = "uzytkownik", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WypozyczenieCzasopisma> wypozyczeniaCzasopisma = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "uzytkownik", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WypozyczenieKsiazki> wypozyczeniaKsiazek = new ArrayList<>();


    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
