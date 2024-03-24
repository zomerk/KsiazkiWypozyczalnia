package com.example.ksiazkiwypozyczalnia.CrudRepo;

import com.example.ksiazkiwypozyczalnia.repo.Articles;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CrudArticles extends PagingAndSortingRepository<Articles,Long>, CrudRepository<Articles,Long>{
    @Query("from Article c where c.title like CONCAT('%',:filter,'%') or c.author like CONCAT('%',:filter,'%') or c.type like CONCAT('%',:filter,'%')")
    List<Articles> findBySearchBar(@Param("filter") String filter);
}
