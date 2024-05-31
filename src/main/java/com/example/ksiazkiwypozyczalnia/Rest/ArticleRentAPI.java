package com.example.ksiazkiwypozyczalnia.Rest;

import com.example.ksiazkiwypozyczalnia.Service.WypozyczenieCzasopismaService;
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
@RequestMapping("/articlerent/api")
public class ArticleRentAPI {
    private WypozyczenieCzasopismaService wypozyczenieCzasopismaService;

    @PostMapping("/create")
    public ResponseEntity<?> createRequest(@RequestParam long czasopismoID){
        return wypozyczenieCzasopismaService.rentCzasopismo(czasopismoID);
    }
    @GetMapping("/acceptList")
    public ResponseEntity<?> acceptList(){
        return wypozyczenieCzasopismaService.CzasopismaForAccept();
    }
    @PostMapping("/accept")
    public ResponseEntity<?> acceptRequest(@RequestParam long id, @RequestParam boolean allowed){
        return wypozyczenieCzasopismaService.RequestOptions(id,allowed);
    }
    @PostMapping("/return")
    public ResponseEntity<?> returnCzasopismo(@RequestParam long id){
        return wypozyczenieCzasopismaService.returnCzasopismo(id);
    }
}
