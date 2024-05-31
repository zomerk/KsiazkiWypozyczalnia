package com.example.ksiazkiwypozyczalnia.Service;

import com.example.ksiazkiwypozyczalnia.CrudRepo.CrudBookRent;
import com.example.ksiazkiwypozyczalnia.repo.WypozyczenieCzasopisma;
import com.example.ksiazkiwypozyczalnia.repo.WypozyczenieKsiazki;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class WypozyczenieKsiazkiService {
    @Autowired
    private CrudBookRent crudBookRent;
    @Autowired
    private KsiazkaService ksiazkaService;
    @Autowired
    private UserService userService;

    public ResponseEntity<?> rentKsiazka(long ksiazkaID) {
        var ksiazka = ksiazkaService.findByID(ksiazkaID);
        if (ksiazka == null) {
            return ResponseEntity.ok("Nie istnieje artykuł o tym ID");
        }
        if (ksiazka.isTaken()) {
            return ResponseEntity.ok("ksiazka jest już wypożyczona przez innego użytkownika.");
        } else {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            var user = userService.findByUserName(auth.getName());
            if (user == null) {
                return ResponseEntity.ok("Użytkownik nie istnieje.");
            }
            var wypozyczenie = new WypozyczenieKsiazki();
            ksiazka.setTaken(true);
            wypozyczenie.setKsiazka(ksiazka);
            wypozyczenie.setUzytkownik(user);
            wypozyczenie.setRentDate(Date.from(Instant.now()));
            crudBookRent.save(wypozyczenie);
            return ResponseEntity.ok("ksiazka wypożyczone czeka na akceptacje właściciela.");
        }
    }

    public ResponseEntity<List<WypozyczenieKsiazki>> KsiazkaForAccept() {
        return ResponseEntity.ok(crudBookRent.forAcceptKsiazki());
    }

    public ResponseEntity<String> RequestOptions(long wypożyczeniaCzasopismaID, boolean allowed) {
        var ksiazka = crudBookRent.findById(wypożyczeniaCzasopismaID).get();
        if(ksiazka.getAllowed().equals("true") || ksiazka.getAllowed().equals("false")){
            return ResponseEntity.ok("ksiazka została już zakceptowana/odrzucona!");
        }
        if (!allowed) {
            var ksiazkaAcces = ksiazka.getKsiazka();
            ksiazkaAcces.setTaken(false);
            ksiazka.setKsiazka(ksiazkaAcces);
            ksiazka.setAllowed("false");
            ksiazka.setReturnDate(Date.from(Instant.now()));
            crudBookRent.save(ksiazka);
            return ResponseEntity.ok("Nie udzielono akceptacji wypożyczenia");
        } else {
            ksiazka.setAllowed("true");
            crudBookRent.save(ksiazka);
            return ResponseEntity.ok("Udzielono wypożyczenia czasopisma");
        }


    }


    public ResponseEntity<?> returnKsiazka(long id) {
        var ksiazka = crudBookRent.findById(id);
        if (ksiazka.isEmpty()) {
            return ResponseEntity.ok("Nie ma wypożyczenego ksiazka o tym numerze");
        } else {
            ksiazka.get().setReturnDate(Date.from(Instant.now()));
            var czas = ksiazka.get().getKsiazka();
            czas.setTaken(false);
            ksiazka.get().setKsiazka(czas);
            crudBookRent.save(ksiazka.get());
            return ResponseEntity.ok("ksiazka " + czas.getTitle() + " został zwrócony");
            //ksiazka.get().set
        }
    }
}
