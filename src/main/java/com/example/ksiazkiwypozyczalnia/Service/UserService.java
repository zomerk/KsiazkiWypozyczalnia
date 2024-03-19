package com.example.ksiazkiwypozyczalnia.Service;

import com.example.ksiazkiwypozyczalnia.CrudRepo.CrudUser;
import com.example.ksiazkiwypozyczalnia.repo.User;
import lombok.Getter;
import lombok.Setter;
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
        User transaction = crudUser
                .findByUsername(username);
        if(transaction == null) {
            System.out.println("User name not found");
            return null;
        }
        else{
            return new UserAdapter(transaction);
        }
    }
    public Boolean CreateUser(User user1){
        var user = loadUserByUsername(user1.getUsername());
        if(user == null){
            crudUser.save(new User(user1.getUsername(), passwordEncoder.encode(user1.getPassword()),"user"));
            return true;
        }
        else{
            return false;
        }
    }
    public List<User> getAllUsers(){
        return crudUser.findAll();
    }
    public User FindByUserName(String username){
        return crudUser.findByUsername(username);
    }
    public void Update(User user){
        crudUser.save(user);
    }
}
