package com.example.ksiazkiwypozyczalnia.Service;

import com.example.ksiazkiwypozyczalnia.CrudRepo.CrudArticles;
import com.example.ksiazkiwypozyczalnia.CrudRepo.CrudBooks;
import com.example.ksiazkiwypozyczalnia.repo.Articles;
import com.example.ksiazkiwypozyczalnia.repo.Books;
import com.example.ksiazkiwypozyczalnia.repo.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryService {
    private final UserService userService;
    private final CrudBooks crudBooks;
    private final CrudArticles crudArticles;


    public LibraryService(UserService userService, CrudBooks crudBooks, CrudArticles crudArticles) {
        this.userService = userService;
        this.crudBooks = crudBooks;
        this.crudArticles = crudArticles;
    }

    public ResponseEntity<List<User>>  GetUsersList(){
        return userService.getAllUsers();

    }
    public ResponseEntity<?> CreateUser(User user){
        if(userService.CreateUser(user)){
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.status(409).body("Username is taken");
        }

    }
    public void CreateBook(Books book){
        crudBooks.save(book);
    }
    public boolean RentBookByUser(String name, long bookId){
        var user = userService.FindByUserName(name);
        var book = crudBooks.findById(bookId);
        if(book.getUser() != null){
            return false;
        }
        book.setUser(user);
        crudBooks.save(book);
        return true;
    }
    public List<Books> GetListOfBooks(String name){
        var use =userService.FindByUserName(name);
        return use.getBorrowedBooks();
    }

    public List<Books> GetAllBooks() {
        List<Books> allBooks = new ArrayList<>();
        crudBooks.findAll().forEach(allBooks::add);
        return allBooks;
    }
    public List<Articles> GetAllArticles() {
        List<Articles> allArticles = new ArrayList<>();
        crudArticles.findAll().forEach(allArticles::add);
        return allArticles;
    }

    public void CreateArticle(Articles articles) {
        crudArticles.save(articles);
    }
    public boolean RentArticleByUser(String name, long articleId){
        var user = userService.FindByUserName(name);
        var articles = crudArticles.findById(articleId).get();
        if(articles.getUser() != null){
            return false;
        }
        else{
            articles.setUser(user);
            crudArticles.save(articles);
            return true;
        }
    }

    public List<Articles> GetListOfArticles(String name) {
        var use =userService.FindByUserName(name);
        return use.getBorrowedArticles();
    }
    public User GetUser(String name){
        return userService.FindByUserName(name);
    }
}

