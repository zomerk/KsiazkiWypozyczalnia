package com.example.ksiazkiwypozyczalnia.CrudRepo;

import com.example.ksiazkiwypozyczalnia.repo.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CrudUser extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

}
