package com.example.ksiazkiwypozyczalnia.CrudRepo;

import com.example.ksiazkiwypozyczalnia.repo.Czasopismo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CrudArticle extends CrudRepository<Czasopismo, Long> {

}
