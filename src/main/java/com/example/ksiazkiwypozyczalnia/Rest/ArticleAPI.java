package com.example.ksiazkiwypozyczalnia.Rest;

import com.example.ksiazkiwypozyczalnia.DTO.ArticleDTO;
import com.example.ksiazkiwypozyczalnia.DTO.BookDTO;
import com.example.ksiazkiwypozyczalnia.Service.CzasopismoService;
import com.example.ksiazkiwypozyczalnia.repo.Czasopismo;
import com.example.ksiazkiwypozyczalnia.repo.Ksiazka;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Getter
@Setter
@AllArgsConstructor
@RestController
@RequestMapping("/article/api")
public class ArticleAPI {
    private CzasopismoService czasopismoService;
    @PostMapping("/create")
    public ResponseEntity<?> createArticle(@RequestBody @Valid ArticleDTO articleDTO) {
        return czasopismoService.createCzasopismo(articleDTO);
    }
    @GetMapping("/articles")
    public Page<Czasopismo> getPaginatedAndSortedArticles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String keyword) {

        if (keyword != null && !keyword.isEmpty()) {
            return czasopismoService.searchArticles(keyword, page, size, sortBy, sortDir);
        }
        return czasopismoService.getPaginatedAndSortedArticle(page, size, sortBy, sortDir);
    }
    @PostMapping("/delete")
    public ResponseEntity<?> deleteArticle(@RequestParam long id) {
        return czasopismoService.deleteArticle(id);
    }
}
