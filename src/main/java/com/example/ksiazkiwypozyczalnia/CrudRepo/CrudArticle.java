package com.example.ksiazkiwypozyczalnia.CrudRepo;

import com.example.ksiazkiwypozyczalnia.repo.Czasopismo;
import com.example.ksiazkiwypozyczalnia.repo.Ksiazka;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CrudArticle extends PagingAndSortingRepository<Czasopismo, Long>, CrudRepository<Czasopismo, Long> {

}
