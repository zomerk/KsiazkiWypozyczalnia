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
    public ResponseEntity<Iterable<Ksiazka>> getAllBooks(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(ksiazkaService.getPaginatedBooks(page,size));
    }
    @GetMapping("/books/sorted")
    public ResponseEntity<Page<Ksiazka>> getAllBooksSorted(@RequestParam int page, @RequestParam int size, @RequestParam String sortBy, @RequestParam String order) {
        return ResponseEntity.ok(ksiazkaService.getPaginatedAndSortedBooks(page,size,sortBy, order));
    }

}
