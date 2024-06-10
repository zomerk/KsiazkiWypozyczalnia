package com.example.ksiazkiwypozyczalnia.Service;

import com.example.ksiazkiwypozyczalnia.CrudRepo.CrudArticle;
import com.example.ksiazkiwypozyczalnia.DTO.ArticleDTO;
import com.example.ksiazkiwypozyczalnia.DTO.Response;
import com.example.ksiazkiwypozyczalnia.DTO.BookDTO;
import com.example.ksiazkiwypozyczalnia.repo.Czasopismo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CzasopismoService {

    @Autowired
    private CrudArticle crudArticle;

    public Page<Czasopismo> getPaginatedAndSortedArticle(int page, int size, String sortBy,String order) {
        Sort sort = Sort.by(sortBy);
        sort = order.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return crudArticle.findAll(pageable);
    }

    public Page<Czasopismo> searchArticles(String keyword, int page, int size, String sortBy, String sortDir) {
        Sort sort = Sort.by(sortBy);
        sort = sortDir.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return crudArticle.search(keyword, pageable);
    }

    public ResponseEntity<?> createCzasopismo(ArticleDTO articleDTO) {
        Czasopismo czasopismo = new Czasopismo();
        czasopismo.setTitle(articleDTO.getTitle());
        czasopismo.setAuthor(articleDTO.getAuthor());
        czasopismo.setLanguage(articleDTO.getLanguage());
        czasopismo.setType(articleDTO.getType());
        czasopismo.setJournal(articleDTO.getJournal());
        czasopismo.setJournalNumber(articleDTO.getJournalNumber());
        crudArticle.save(czasopismo);
        return ResponseEntity.ok(new Response("Artykuł o nazwie " + articleDTO.getTitle() +" został zapisany."));

    }

    public Czasopismo findByID(long czasopismoID) {
        var czasopismo = crudArticle.findById(czasopismoID);
        return czasopismo.orElse(null);
    }
    public ResponseEntity<?> deleteArticle(long ID) {
        if(crudArticle.findById(ID).isPresent()) {
            crudArticle.deleteById(ID);
            return ResponseEntity.ok("Artykuł usunięta");
        }
        else{
            return ResponseEntity.notFound().build();
        }

    }
}