package com.example.ksiazkiwypozyczalnia.Rest;

import com.example.ksiazkiwypozyczalnia.Service.LibraryService;
import com.example.ksiazkiwypozyczalnia.Service.UserService;
import com.example.ksiazkiwypozyczalnia.repo.Articles;
import com.example.ksiazkiwypozyczalnia.repo.Books;
import com.example.ksiazkiwypozyczalnia.repo.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class API {
    private final LibraryService libraryService;

    public API(LibraryService libraryService) {
        this.libraryService = libraryService;
    }
    @PostMapping("/user")
    void createUser(@RequestBody User user){
        libraryService.CreateUser(user);
    }
    @GetMapping("/user")
    List<User> getAllUsers(){
        return libraryService.GetUsersList();
    }
    @PostMapping("/rentbook")
    public void RentBookByUser(@RequestParam long bookId){
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        libraryService.RentBookByUser(authentication.getName(),bookId);
    }
    @PostMapping("/rentarticle")
    public void RentArticleByUser(@RequestParam long ArticleId){
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        libraryService.RentArticleByUser(authentication.getName(),ArticleId);
    }
    @PostMapping("/book")
    public void createBook(@RequestBody Books book){
        libraryService.CreateBook(book);
    }
    @GetMapping("/book")
    public List<Books> GetListOfBooks(){
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return libraryService.GetListOfBooks(authentication.getName());
    }
    @GetMapping("/books")
    public List<Books> GetAllBooks(){
        return libraryService.GetAllBooks();
    }
    @GetMapping("/articles")
    public List<Articles> GetAllArticles(){
        return libraryService.GetAllArticles();
    }
    @PostMapping("/article")
    public void createArticle(@RequestBody Articles articles){
        libraryService.CreateArticle(articles);
    }
    @GetMapping("/article")
    public List<Articles> GetListOfArticles(){
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return libraryService.GetListOfArticles(authentication.getName());
    }

}
