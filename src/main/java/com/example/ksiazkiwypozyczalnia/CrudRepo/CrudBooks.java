package com.example.ksiazkiwypozyczalnia.CrudRepo;

import com.example.ksiazkiwypozyczalnia.repo.Books;
import org.springframework.data.repository.CrudRepository;

public interface CrudBooks extends CrudRepository<Books,Long> {
    Books findById(long id);
}
