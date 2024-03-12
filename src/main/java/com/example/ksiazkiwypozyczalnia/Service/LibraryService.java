package com.example.ksiazkiwypozyczalnia.Service;

import com.example.ksiazkiwypozyczalnia.CrudRepo.CrudLibrary;
import com.example.ksiazkiwypozyczalnia.repo.Library;
import org.apache.catalina.util.LifecycleBase;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {
    private final CrudLibrary library;

    public LibraryService(CrudLibrary library) {
        this.library = library;
    }

    public ResponseEntity<?> CreateQuiz(Library libraryforsave){
        library.save(libraryforsave);
        return ResponseEntity.ok().build();
    }
    public List<Library> GetAllQuizes(){
        return library.findAll();
    }
    public List<Library> BooksRentedByUser(String user){return  library.findAllByUserUsername(user);}
    public List<Library> BooksByAuthor(String name){return library.findAllByAuthor(name);}
    public List<Library> BooksByType(String type){return library.findAllByType(type);}
    public List<Library> BooksByPagesMore(int number){return  library.findAllByPagesGreaterThan(number);}
    public List<Library> BooksByPagesLess(int number){return  library.findAllByPagesLessThan(number);}
    public Library FindByID(long id) {
        if (library.findById(id).isPresent()) {
            return library.findById(id).get();
        } else {
            return null;
        }
    }

    public void Update(Library collection) {
        library.save(collection);
    }
}

