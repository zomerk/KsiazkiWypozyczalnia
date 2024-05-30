package com.example.ksiazkiwypozyczalnia.CrudRepo;

import com.example.ksiazkiwypozyczalnia.repo.Czasopismo;
import com.example.ksiazkiwypozyczalnia.repo.Ksiazka;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface CrudArticle extends PagingAndSortingRepository<Czasopismo, Long>, CrudRepository<Czasopismo, Long> {
    @Query("SELECT c FROM Czasopismo c WHERE c.title LIKE %:keyword% OR c.journal LIKE %:keyword% OR c.author LIKE %:keyword%")
    Page<Czasopismo> search(@Param("keyword") String keyword, Pageable pageable);

}
