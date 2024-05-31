package com.example.ksiazkiwypozyczalnia.Rest;

import com.example.ksiazkiwypozyczalnia.Service.WypozyczenieKsiazkiService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Getter
@Setter
@AllArgsConstructor
@RestController
@RequestMapping("/bookrent/api")
public class BookRentAPI {
    @Autowired
    private WypozyczenieKsiazkiService wypozyczenieKsiazkiService;

    @PostMapping("/create")
    public ResponseEntity<?> createRequest(@RequestParam long ksiazkaID){
        return wypozyczenieKsiazkiService.rentKsiazka(ksiazkaID);
    }
    @GetMapping("/acceptList")
    public ResponseEntity<?> acceptList(){
        return wypozyczenieKsiazkiService.KsiazkaForAccept();
    }
    @PostMapping("/accept")
    public ResponseEntity<?> acceptRequest(@RequestParam long id, @RequestParam boolean allowed){
        return wypozyczenieKsiazkiService.RequestOptions(id,allowed);
    }
    @PostMapping("/return")
    public ResponseEntity<?> returnKsiazka(@RequestParam long id){
        return wypozyczenieKsiazkiService.returnKsiazka(id);
    }
}
