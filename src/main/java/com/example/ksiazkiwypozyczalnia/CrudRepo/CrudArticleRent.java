package com.example.ksiazkiwypozyczalnia.CrudRepo;

import com.example.ksiazkiwypozyczalnia.repo.WypozyczenieCzasopisma;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CrudArticleRent extends CrudRepository<WypozyczenieCzasopisma,Long> {

    @Query("SELECT w FROM WypozyczenieCzasopisma w WHERE w.rentDate IS NOT NULL AND w.returnDate IS NULL AND w.allowed = 'null'")
    List<WypozyczenieCzasopisma> forAcceptCzasopisma();
}
