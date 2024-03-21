package com.example.ksiazkiwypozyczalnia.CrudRepo;

import com.example.ksiazkiwypozyczalnia.repo.Articles;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CrudArticles extends CrudRepository<Articles,Long> {
}
