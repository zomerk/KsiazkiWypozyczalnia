package com.example.ksiazkiwypozyczalnia.CrudRepo;

import com.example.ksiazkiwypozyczalnia.repo.Ksiazka;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;


public interface CrudBook extends PagingAndSortingRepository<Ksiazka, Long>, CrudRepository<Ksiazka,Long> {

}
