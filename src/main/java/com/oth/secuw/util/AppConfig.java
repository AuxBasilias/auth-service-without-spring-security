package com.oth.secuw.util;

import com.oth.secuw.entities.User;

public class AppConfig {
    private static final AppConfig instance = new AppConfig();
    //private String myVariable = "initialValue";
    private boolean autorized ;
    private User.Role role;
    private User userConnected;


    private AppConfig() {}

    public static AppConfig getInstance() {
        return instance;
    }

    public boolean getAutorized() {
        return autorized;
    }

    public void setAutorized(boolean autorized) {
        this.autorized = autorized;
    }

    public User.Role getRole(){
        return role;
    }
    public void setRole(User.Role role){
        this.role = role;
    }

    public User getUserConnected() {
        return userConnected;
    }

    public void setUserConnected(User userConnected) {
        this.userConnected = userConnected;
    }
}