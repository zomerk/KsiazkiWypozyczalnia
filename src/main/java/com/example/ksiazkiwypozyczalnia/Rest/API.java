package com.example.ksiazkiwypozyczalnia.Rest;

import com.example.ksiazkiwypozyczalnia.Service.LibraryService;
import com.example.ksiazkiwypozyczalnia.Service.UserService;
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

    public API(UserService userService, LibraryService libraryService) {
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
    public void RentBookByUser(@RequestParam String name, @RequestParam long bookId){
        libraryService.RentBookByUser(name,bookId);
    }
    @PostMapping("/book")
    public void createBook(@RequestBody Books book){
        libraryService.CreateBook(book);
    }
    @GetMapping("/book")
    public List<Books> GetListOfBooks(@RequestParam String name){
        return libraryService.GetListOfBooks(name);
    }

}
