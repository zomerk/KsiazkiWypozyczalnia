package com.example.ksiazkiwypozyczalnia.CrudRepo;

import com.example.ksiazkiwypozyczalnia.repo.Ksiazka;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface CrudBook extends CrudRepository<Ksiazka, Long> {
}
