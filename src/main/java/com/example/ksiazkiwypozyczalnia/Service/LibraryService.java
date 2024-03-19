package com.example.ksiazkiwypozyczalnia.Service;

import com.example.ksiazkiwypozyczalnia.CrudRepo.CrudBooks;
import com.example.ksiazkiwypozyczalnia.repo.Books;
import com.example.ksiazkiwypozyczalnia.repo.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class LibraryService {
    private final UserService userService;
    private final CrudBooks crudBooks;


    public LibraryService(UserService userService, CrudBooks crudBooks) {
        this.userService = userService;
        this.crudBooks = crudBooks;
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
        var list = user.getBorrowedBooks();
        list.add(book);
        user.setBorrowedBooks(list);
        //userService.Update(user);
        book.setUser(user);
        crudBooks.save(book);
    }
    public List<Books> GetListOfBooks(String name){
        var use =userService.FindByUserName(name);
        return use.getBorrowedBooks();
    }

}

