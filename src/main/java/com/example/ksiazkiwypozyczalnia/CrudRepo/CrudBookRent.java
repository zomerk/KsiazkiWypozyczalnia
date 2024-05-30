package com.example.ksiazkiwypozyczalnia.CrudRepo;

import com.example.ksiazkiwypozyczalnia.repo.WypozyczenieKsiazki;
import org.springframework.data.repository.CrudRepository;

public interface CrudBookRent extends CrudRepository<WypozyczenieKsiazki,Long> {
}
