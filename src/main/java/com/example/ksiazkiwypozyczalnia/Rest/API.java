package com.example.ksiazkiwypozyczalnia.Rest;

import com.example.ksiazkiwypozyczalnia.Service.LibraryService;
import com.example.ksiazkiwypozyczalnia.Service.UserService;
import com.example.ksiazkiwypozyczalnia.repo.Library;
import com.example.ksiazkiwypozyczalnia.repo.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class API {
    private final UserService userService;
    private final LibraryService libraryService;

    public API(UserService userService, LibraryService libraryService) {
        this.userService = userService;
        this.libraryService = libraryService;
    }
    @GetMapping("/books")
    public ResponseEntity<?> GetAllBooks(){
        return ResponseEntity.ok().body(libraryService.GetAllQuizes());
    }
    @GetMapping("/books/user")
    public ResponseEntity<?> GetAllBooksByUser(@RequestParam String user){
        return ResponseEntity.ok().body(libraryService.BooksRentedByUser(user));
    }
    @GetMapping("/books/author")
    public ResponseEntity<?> GetAllBooksByAuthor(@RequestParam String author){
        return ResponseEntity.ok().body(libraryService.BooksByAuthor(author));
    }
    @GetMapping("/books/type")
    public ResponseEntity<?> GetAllBooksByType(@RequestParam String type){
        return ResponseEntity.ok().body(libraryService.BooksByType(type));
    }
    @GetMapping("/books/more")
    public ResponseEntity<?> GetAllBooksByPagesMore(@RequestParam int count){
        return ResponseEntity.ok().body(libraryService.BooksByPagesMore(count));
    }
    @GetMapping("/books/less")
    public ResponseEntity<?> GetAllBooksByLess(@RequestParam int count){
        return ResponseEntity.ok().body(libraryService.BooksByPagesLess(count));
    }
    @PostMapping("/books/add")
    public ResponseEntity<?> addBookToCollection(@Validated @RequestBody Library library){
        libraryService.CreateQuiz(library);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/books/add_List")
    public ResponseEntity<?> addBookToCollectionList(@Validated @RequestBody List<Library> library){
        for(var lib:library){
            libraryService.CreateQuiz(lib);
        }
        return ResponseEntity.ok().build();
    }
    @PostMapping("books/createUser")
    public ResponseEntity<?> addUser(@Validated @RequestBody User user){
        if(userService.CreateUser(user)){
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.ok().body("The username was taken");
        }
    }
    @PostMapping("books/borrow")
    public ResponseEntity<?> BorrowedBooks(@RequestParam int id){
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var Collection = libraryService.FindByID(id);
        if(Collection == null){
            return ResponseEntity.ok().body("ID not found");
        }
        var name = authentication.getName();
        var User = userService.FindByUserName(name);
        var Books = User.getBorrowedBooks();
        Books.add(Collection);
        User.setBorrowedBooks(Books);
        userService.Update(User);
        var Users = Collection.getUser();
        Users.add(User);
        Collection.setUser(Users);
        libraryService.Update(Collection);
        return ResponseEntity.ok().build();
    }

}
