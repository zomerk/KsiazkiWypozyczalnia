package com.example.ksiazkiwypozyczalnia.Rest;

import com.example.ksiazkiwypozyczalnia.Service.LibraryService;
import com.example.ksiazkiwypozyczalnia.repo.Articles;
import com.example.ksiazkiwypozyczalnia.repo.Books;
import com.example.ksiazkiwypozyczalnia.repo.ReserveBooks;
import com.example.ksiazkiwypozyczalnia.repo.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class API {
    private final LibraryService libraryService;

    public API(LibraryService libraryService) {
        this.libraryService = libraryService;
    }
    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        return libraryService.CreateUser(user);
    }
    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers(){
        return libraryService.GetUsersList();
    }
    @PostMapping("/rentbook")
    public ResponseEntity<?> RentBookByUser(@RequestParam long bookId){
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = libraryService.GetUser(authentication.getName());
        if(user.getBorrowedBooks().size() >= 5){
            return ResponseEntity.status(403).body("Limit ksiązek wyczerpany");
        }
        if(libraryService.RentBookByUser(authentication.getName(),bookId)){
            return ResponseEntity.ok().body("Chęć wypożycznie ksiązki wysłana do administratora");
        }
        else{
            return ResponseEntity.status(404).body("Książka juz jest wypozyczona");
        }

    }
    @PostMapping("/rentarticle")
    public ResponseEntity<?> RentArticleByUser(@RequestParam long articleId){
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = libraryService.GetUser(authentication.getName());
        if(user.getBorrowedArticles().size() >= 5){
            return ResponseEntity.status(403).body("Limit artykułów wyczerpany");
        }
        if(libraryService.RentArticleByUser(authentication.getName(),articleId)){
            return ResponseEntity.ok().body("Chęć wypożycznie artykłu wysłana do administratora");
        }
        else{
            return ResponseEntity.status(404).body("Artykuł juz jest wypozyczona");
        }
    }
    @PostMapping("/book")
    public ResponseEntity<?> createBook(@RequestBody Books book){
        libraryService.CreateBook(book);
        return ResponseEntity.ok().body("Książka dodana");
    }
    @GetMapping("/book")
    public ResponseEntity<List<Books>> GetListOfBooks(){
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok().body(libraryService.GetListOfBooks(authentication.getName()));
    }
    @GetMapping("/books")
    public ResponseEntity<List<Books>> GetAllBooks(){
        return ResponseEntity.ok().body(libraryService.GetAllBooks());
    }
    @GetMapping("/articles")
    public ResponseEntity<List<Articles>> GetAllArticles(){
        return ResponseEntity.ok().body(libraryService.GetAllArticles());
    }
    @PostMapping("/article")
    public ResponseEntity<?> createArticle(@RequestBody Articles articles){
        libraryService.CreateArticle(articles);
        return ResponseEntity.ok().body("Artykuł dodany");
    }
    @GetMapping("/article")
    public ResponseEntity<List<Articles>> GetListOfArticles(){
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok().body(libraryService.GetListOfArticles(authentication.getName()));
    }
    @GetMapping("/reserve")
    public ResponseEntity<List<ReserveBooks>> GetWantedBooksByUser(@RequestParam String username){
        return ResponseEntity.ok(libraryService.GetWantedBooks(username));
    }

}
