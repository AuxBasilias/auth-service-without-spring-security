package com.oth.secuw.controller;

import com.oth.secuw.dto.LoginDto;
import com.oth.secuw.dto.SigninDto;
import com.oth.secuw.entities.User;
import com.oth.secuw.repo.UserRepo;
import com.oth.secuw.util.AppConfig;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ApiController {
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/signup")
    public ResponseEntity<?> saveUser(@RequestBody SigninDto signinDto){
        try {
            User newUser = new User();
            newUser.setUsername(signinDto.getUsername());
            newUser.setEmail(signinDto.getEmail());
            newUser.setPassword(signinDto.getPassword());
            newUser.setRole(signinDto.getRole());
            userRepo.save(newUser);
            System.out.println(newUser.getRole());
            String message ="User "+newUser.getUsername()+" added";
            return ResponseEntity.ok(message);

        }catch (Exception ex){
            String message = "An error occurred while registering the User ";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }

    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto){
        Optional<User> found = userRepo.findByUsernameOrEmail(loginDto.getUsernameOremail(), loginDto.getUsernameOremail());
        if(found.isPresent()){
            User user = found.get();
            Argon2 argon2 = Argon2Factory.create();
            if(argon2.verify(user.getPassword(), loginDto.getPassword().toCharArray() )){
                AppConfig.getInstance().setAutorized(true);
                AppConfig.getInstance().setRole(user.getRole());
                AppConfig.getInstance().setUserConnected(user);
                String message = "Vous êtes connecté";
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
            }else{
                AppConfig.getInstance().setAutorized(false);
                String message = "Identifiant et mot de passe invalide";
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
            }
        }else{
            String message = "User not found";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }

    }

    @GetMapping("/confirm")
    public String confirm(){
        if(AppConfig.getInstance().getAutorized()){
            return "Vous êtes bien connecté";
        }
        else{
            return "Vous n'êtes pas connecté";
        }

    }

    @GetMapping("/authenrization")
    public String authenrization(){
        if (AppConfig.getInstance().getRole() == null){
            return "You are nothing 😂😂😂";
        }else{
            if(AppConfig.getInstance().getRole().equals(User.Role.valueOf("ADMIN"))){
                return "You are an Admin";
            }
            else if(AppConfig.getInstance().getRole().equals(User.Role.valueOf("USER"))){
                return "You are a User";
            }
        }
        return null;
    }
    @GetMapping("/authorization/admin")
    public String admin(){
        if(AppConfig.getInstance().getAutorized()){
            if(AppConfig.getInstance().getRole().equals(User.Role.valueOf("ADMIN"))){
                return "Only Admins are authorized here";
            }else{
                return "You are not an admin";
            }
        }else{
            return "You are not authorized";
        }

    }
    @GetMapping("/authorization/user")
    public String user(){
        if(AppConfig.getInstance().getAutorized()){
            if(AppConfig.getInstance().getRole().equals(User.Role.valueOf("USER"))){
                return "Only Users are authorized here";
            }else{
                return "You are not an user";
            }
        }else{
            return "You are not authorized";
        }

    }

    @GetMapping("/who_is_connected")
    public ResponseEntity<?> whoIsConnected(){
        if(AppConfig.getInstance().getAutorized()){
            return ResponseEntity.ok(AppConfig.getInstance().getUserConnected());
        }else{
            String message = "You are not connected";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }

    }

    @GetMapping("/logout")
    public String logout(){
        if(AppConfig.getInstance().getAutorized()){
            AppConfig.getInstance().setUserConnected(null);
            AppConfig.getInstance().setRole(null);
            AppConfig.getInstance().setAutorized(false);
            return "You are disconnected";
        }else{
            return "You are not authorized";
        }
    }
}
