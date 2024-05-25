package com.example.ksiazkiwypozyczalnia.Service;

import com.example.ksiazkiwypozyczalnia.CrudRepo.CrudUser;
import com.example.ksiazkiwypozyczalnia.repo.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final CrudUser crudUser;
    PasswordEncoder passwordEncoder;


    public UserService(CrudUser crudUser, PasswordEncoder passwordEncoder) {
        this.crudUser = crudUser;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(crudUser.findByUsername(username).isEmpty()) {
            System.out.println("User name not found");
            return null;
        }
        else{
            User transaction = crudUser
                    .findByUsername(username).get();
            return new UserAdapter(transaction);
        }
    }
    public ResponseEntity<?> CreateUser(String username, String password){
        var user = crudUser.findByUsername(username);
        if(user.isEmpty()){
            crudUser.save(new User(username, passwordEncoder.encode(password),"user"));
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.ok("User already exists");
        }
    }
}
