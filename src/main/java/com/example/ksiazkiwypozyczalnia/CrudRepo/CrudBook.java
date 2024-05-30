package com.example.ksiazkiwypozyczalnia.CrudRepo;

import com.example.ksiazkiwypozyczalnia.repo.Czasopismo;
import com.example.ksiazkiwypozyczalnia.repo.Ksiazka;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;



public interface CrudBook extends PagingAndSortingRepository<Ksiazka, Long>, CrudRepository<Ksiazka,Long> {
    @Query("SELECT c FROM Ksiazka c WHERE c.title LIKE %:keyword% OR c.type LIKE %:keyword% OR c.author LIKE %:keyword%")
    Page<Ksiazka> search(@Param("keyword") String keyword, Pageable pageable);

}
