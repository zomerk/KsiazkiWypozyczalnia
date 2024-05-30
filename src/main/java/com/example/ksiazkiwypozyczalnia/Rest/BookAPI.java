package com.example.ksiazkiwypozyczalnia.Rest;

import com.example.ksiazkiwypozyczalnia.DTO.BookDTO;
import com.example.ksiazkiwypozyczalnia.Service.KsiazkaService;
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
@RequestMapping("/book/api")
public class BookAPI {

    private KsiazkaService ksiazkaService;
    @PostMapping("/create")
    public ResponseEntity<?> createBook(@RequestBody @Valid BookDTO bookDTO) {
        return ksiazkaService.createBook(bookDTO);
    }
    @GetMapping("/books")
    public Page<Ksiazka> getPaginatedAndSortedBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String keyword) {

        if (keyword != null && !keyword.isEmpty()) {
            return ksiazkaService.searchBooks(keyword, page, size, sortBy, sortDir);
        }
        return ksiazkaService.getPaginatedAndSortedBooks(page, size, sortBy, sortDir);
    }

}
