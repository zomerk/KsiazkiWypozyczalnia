package com.example.ksiazkiwypozyczalnia.Service;

import com.example.ksiazkiwypozyczalnia.CrudRepo.CrudArticles;
import com.example.ksiazkiwypozyczalnia.CrudRepo.CrudBooks;
import com.example.ksiazkiwypozyczalnia.CrudRepo.CrudReserveBooks;
import com.example.ksiazkiwypozyczalnia.repo.Articles;
import com.example.ksiazkiwypozyczalnia.repo.Books;
import com.example.ksiazkiwypozyczalnia.repo.ReserveBooks;
import com.example.ksiazkiwypozyczalnia.repo.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
public class LibraryService {
    private final UserService userService;
    private final CrudBooks crudBooks;
    private final CrudArticles crudArticles;
    private final CrudReserveBooks crudReserveBooks;


    public LibraryService(UserService userService, CrudBooks crudBooks, CrudArticles crudArticles, CrudReserveBooks crudReserveBooks) {
        this.userService = userService;
        this.crudBooks = crudBooks;
        this.crudArticles = crudArticles;
        this.crudReserveBooks = crudReserveBooks;
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
        if(book.isTaken()){
            return false;
        }
        var reserve = new ReserveBooks(user.getUsername(),bookId,true);
        book.setTaken(true);
        crudBooks.save(book);
        crudReserveBooks.save(reserve);
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
        if(articles.isTaken()){
            return false;
        }
        else{
            var reserve = new ReserveBooks(user.getUsername(),articleId,false);
            articles.setTaken(true);
            crudArticles.save(articles);
            crudReserveBooks.save(reserve);
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
    public List<ReserveBooks> GetWantedBooks(String username){
        return crudReserveBooks.findAllByUserName(username);
    }

    public List<Books> SearchByNameBooks(String term) {

        return crudBooks.findBySearchBar( term);
    }

    public List<Articles> SearchByNameArticle(String term) {
        System.out.println(term);
        return crudArticles.findBySearchBar( term);
    }
}

//    public boolean RentBookByUser(String name, long bookId){
//        var user = userService.FindByUserName(name);
//        var book = crudBooks.findById(bookId);
//        if(book.getUser() != null){
//            return false;
//        }
//        book.setUser(user);
//        crudBooks.save(book);
//        return true;
//    }
