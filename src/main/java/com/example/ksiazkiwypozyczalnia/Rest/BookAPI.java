package com.example.ksiazkiwypozyczalnia.Rest;

import com.example.ksiazkiwypozyczalnia.CrudRepo.CrudBook;
import com.example.ksiazkiwypozyczalnia.DTO.BookDTO;
import com.example.ksiazkiwypozyczalnia.repo.Ksiazka;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RestController
@RequestMapping("/book/api")
public class BookAPI {

    private CrudBook crudBook;
    @PostMapping("/create")
    public ResponseEntity<?> createBook(@RequestBody @Valid BookDTO bookDTO) {
        var book = new Ksiazka();
        book.setAuthor(bookDTO.getAuthor());
        book.setTitle(bookDTO.getTitle());
        book.setLanguage(bookDTO.getLanguage());
        book.setType(bookDTO.getType());
        book.setYear(bookDTO.getYear());
        book.setTaken(bookDTO.isTaken());
        crudBook.save(book);
        return ResponseEntity.ok("Książka o tytule "+ bookDTO.getTitle() + " zapisana pomyślnie.");
    }
    @GetMapping("/books")
    public ResponseEntity<Iterable<Ksiazka>> getAllBooks() {
        return ResponseEntity.ok(crudBook.findAll());
    }

}
