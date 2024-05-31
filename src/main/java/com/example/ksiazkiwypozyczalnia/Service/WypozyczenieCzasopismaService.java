package com.example.ksiazkiwypozyczalnia.Service;

import com.example.ksiazkiwypozyczalnia.CrudRepo.CrudArticleRent;
import com.example.ksiazkiwypozyczalnia.CrudRepo.CrudBookRent;
import com.example.ksiazkiwypozyczalnia.repo.Czasopismo;
import com.example.ksiazkiwypozyczalnia.repo.WypozyczenieCzasopisma;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class WypozyczenieCzasopismaService {
    @Autowired
    private CrudArticleRent crudArticleRent;
    @Autowired
    private CzasopismoService czasopismoService;
    @Autowired
    private UserService userService;

    public ResponseEntity<?> rentCzasopismo(long czasopismoID) {
        var czasopismo = czasopismoService.findByID(czasopismoID);
        if (czasopismo == null) {
            return ResponseEntity.ok("Nie istnieje artykuł o tym ID");
        }
        if (czasopismo.isTaken()) {
            return ResponseEntity.ok("Czasopismo jest już wypożyczona przez innego użytkownika.");
        } else {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            var user = userService.findByUserName(auth.getName());
            if (user == null) {
                return ResponseEntity.ok("Użytkownik nie istnieje.");
            }
            var wypozyczenie = new WypozyczenieCzasopisma();
            czasopismo.setTaken(true);
            wypozyczenie.setCzasopismo(czasopismo);
            wypozyczenie.setUzytkownik(user);
            wypozyczenie.setRentDate(Date.from(Instant.now()));
            crudArticleRent.save(wypozyczenie);
            return ResponseEntity.ok("Czasopismo wypożyczone czeka na akceptacje właściciela.");
        }
    }

    public ResponseEntity<List<WypozyczenieCzasopisma>> CzasopismaForAccept() {
        return ResponseEntity.ok(crudArticleRent.forAcceptCzasopisma());
    }

    public ResponseEntity<String> RequestOptions(long wypożyczeniaCzasopismaID, boolean allowed) {
        var czasopismo = crudArticleRent.findById(wypożyczeniaCzasopismaID).get();
        if(czasopismo.getAllowed().equals("true") || czasopismo.getAllowed().equals("false")){
            return ResponseEntity.ok("Czasopismo została już zakceptowana/odrzucona!");
        }
        if (!allowed) {
            var czasopismoAcces = czasopismo.getCzasopismo();
            czasopismoAcces.setTaken(false);
            czasopismo.setCzasopismo(czasopismoAcces);
            czasopismo.setAllowed("false");
            czasopismo.setReturnDate(Date.from(Instant.now()));
            crudArticleRent.save(czasopismo);
            return ResponseEntity.ok("Nie udzielono akceptacji wypożyczenia");
        } else {
            czasopismo.setAllowed("true");
            crudArticleRent.save(czasopismo);
            return ResponseEntity.ok("Udzielono wypożyczenia czasopisma");
        }


    }


    public ResponseEntity<?> returnCzasopismo(long id) {
        var czasopismo = crudArticleRent.findById(id);
        if (czasopismo.isEmpty()) {
            return ResponseEntity.ok("Nie ma wypożyczenego czasopismo o tym numerze");
        } else {
            czasopismo.get().setReturnDate(Date.from(Instant.now()));
            var czas = czasopismo.get().getCzasopismo();
            czas.setTaken(false);
            czasopismo.get().setCzasopismo(czas);
            crudArticleRent.save(czasopismo.get());
            return ResponseEntity.ok("Czasopismo " + czas.getTitle() + " został zwrócony");
            //czasopismo.get().set
        }
    }
}
