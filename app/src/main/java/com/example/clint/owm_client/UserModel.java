package com.example.clint.owm_client;

public class UserModel {
    private String username;
    private int favone;
    private int favtwo;
    private int favthree;
    private int favfour;
    private int favfive;

    public UserModel(){}

    public UserModel(String id) {
        super();
        this.username = id;
        favone = 0;
        favtwo = 0;
        favthree = 0;
        favfour = 0;
        favfive = 0;
    }

    public void setUsername(String id) {
        this.username = id;
    }

    public String getUsername() {
        return username;
    }

    public void setFavone(int id) {
        this.favone = id;
    }

    public int getFavone() {
        return favone;
    }

    public void setFavtwo(int id) {
        this.favtwo = id;
    }

    public int getFavtwo() {
        return favtwo;
    }

    public void setFavthree(int id) {
        this.favthree = id;
    }

    public int getFavthree() {
        return favthree;
    }

    public void setFavfour(int id) {
        this.favfour = id;
    }

    public int getFavfour() {
        return favfour;
    }

    public void setFavfive(int id) {
        this.favfive = id;
    }

    public int getFavfive() {
        return favfive;
    }

    @Override
    public String toString() {
        return "User [Username=" + username + ", Fav One=" + favone + ", Fav Two=" + favtwo +
                ", Fav Three=" + favthree + ", Fav Four=" + favfour + ", Fav Five=" + favfive
                + "]";
    }
}
