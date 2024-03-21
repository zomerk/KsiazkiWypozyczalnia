package com.example.ksiazkiwypozyczalnia.CrudRepo;

import com.example.ksiazkiwypozyczalnia.repo.ReserveBooks;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface CrudReserveBooks extends CrudRepository<ReserveBooks,Long> {
    public List<ReserveBooks> findAllByUserName(String username);
}
