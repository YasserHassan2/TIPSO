package com.example.games_tip_and_trick.Models;

public class Game {
    int id;
    String imageURL,title;

    public Game(int id, String imageURL, String title) {
        this.id = id;
        this.imageURL = imageURL;
        this.title = title;
    }

    public Game() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
