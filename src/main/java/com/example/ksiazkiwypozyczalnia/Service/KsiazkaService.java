package com.example.ksiazkiwypozyczalnia.Service;

import com.example.ksiazkiwypozyczalnia.CrudRepo.CrudBook;
import com.example.ksiazkiwypozyczalnia.DTO.BookDTO;
import com.example.ksiazkiwypozyczalnia.repo.Czasopismo;
import com.example.ksiazkiwypozyczalnia.repo.Ksiazka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class KsiazkaService {

    @Autowired
    private CrudBook crudBook;

    public Page<Ksiazka> getPaginatedAndSortedBooks(int page, int size, String sortBy, String order) {
        Sort sort = Sort.by(sortBy);
        sort = order.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return crudBook.findAll(pageable);
    }

    public Page<Ksiazka> searchBooks(String keyword, int page, int size, String sortBy, String sortDir) {
        Sort sort = Sort.by(sortBy);
        sort = sortDir.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return crudBook.search(keyword, pageable);
    }

    public ResponseEntity<?> createBook(BookDTO bookDTO) {
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
}
