package com.example.ksiazkiwypozyczalnia.CrudRepo;

import com.example.ksiazkiwypozyczalnia.repo.Articles;
import com.example.ksiazkiwypozyczalnia.repo.Books;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CrudBooks extends PagingAndSortingRepository<Books,Long>,CrudRepository<Books,Long>{
    Books findById(long id);
    @Query("from Books c where c.title like CONCAT('%',:filter,'%') or c.author like CONCAT('%',:filter,'%') or c.type like CONCAT('%',:filter,'%')")
    List<Books> findBySearchBar(@Param("filter") String filter);
}
