package com.example.ksiazkiwypozyczalnia.CrudRepo;

import com.example.ksiazkiwypozyczalnia.repo.Library;
import com.example.ksiazkiwypozyczalnia.repo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrudLibrary extends JpaRepository<Library, Long> {
    List<Library> findAllByUserUsername(String user);
    List<Library> findAllByAuthor(String name);
    List<Library> findAllByType(String name);
    List<Library> findAllByPagesGreaterThan(int value);
    List<Library> findAllByPagesLessThan(int value);




}
