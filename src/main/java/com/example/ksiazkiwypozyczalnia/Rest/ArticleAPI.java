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
    @GetMapping("/article")
    public ResponseEntity<Page<Czasopismo>> getAllBooks(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(czasopismoService.getPaginatedArticle(page,size));
    }
    @GetMapping("/article/sorted")
    public ResponseEntity<Page<Czasopismo>> getAllBooksSorted(@RequestParam int page, @RequestParam int size, @RequestParam String sortBy, @RequestParam String order) {
        return ResponseEntity.ok(czasopismoService.getPaginatedAndSortedArticle(page,size,sortBy,order));
    }
}
