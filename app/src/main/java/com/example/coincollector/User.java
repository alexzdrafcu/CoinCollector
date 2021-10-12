package com.example.coincollector;

import java.util.ArrayList;

public class User {
    public String nume, email;
    public ArrayList<Coin> coins;
    public User() {

    }

    public User(String nume, String email){
        this.nume = nume;
        this.email = email;
    }

    public User(String nume, String email, ArrayList<Coin> coins){
        this.nume = nume;
        this.email = email;
        this.coins = coins;
    }

    @Override
    public String toString() {
        return "User{" +
                "nume='" + nume + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
