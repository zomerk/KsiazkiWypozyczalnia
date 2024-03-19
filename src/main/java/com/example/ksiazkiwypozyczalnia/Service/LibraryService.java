package com.example.ksiazkiwypozyczalnia.Service;

import com.example.ksiazkiwypozyczalnia.CrudRepo.CrudArticles;
import com.example.ksiazkiwypozyczalnia.CrudRepo.CrudBooks;
import com.example.ksiazkiwypozyczalnia.repo.Articles;
import com.example.ksiazkiwypozyczalnia.repo.Books;
import com.example.ksiazkiwypozyczalnia.repo.User;
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

    public List<User>  GetUsersList(){
        return userService.getAllUsers();

    }
    public void CreateUser(User user){
        userService.CreateUser(user);

    }
    public void CreateBook(Books book){
        crudBooks.save(book);
    }
    public void RentBookByUser(String name, long bookId){
        var user = userService.FindByUserName(name);
        var book = crudBooks.findById(bookId);
        book.setUser(user);
        crudBooks.save(book);
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
    public void RentArticleByUser(String name, long articleId){
        var user = userService.FindByUserName(name);
        var articles = crudArticles.findById(articleId).get();
        articles.setUser(user);
        crudArticles.save(articles);
    }

    public List<Articles> GetListOfArticles(String name) {
        var use =userService.FindByUserName(name);
        return use.getBorrowedArticles();
    }
}

