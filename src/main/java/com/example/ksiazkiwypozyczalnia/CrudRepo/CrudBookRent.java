package com.example.ksiazkiwypozyczalnia.CrudRepo;

import com.example.ksiazkiwypozyczalnia.repo.WypozyczenieCzasopisma;
import com.example.ksiazkiwypozyczalnia.repo.WypozyczenieKsiazki;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CrudBookRent extends CrudRepository<WypozyczenieKsiazki,Long> {
    @Query("SELECT w FROM WypozyczenieKsiazki w WHERE w.rentDate IS NOT NULL AND w.returnDate IS NULL AND w.allowed = 'null'")
    List<WypozyczenieKsiazki> forAcceptKsiazki();
}
