package com.example.games_tip_and_trick.Models;

public class Topic {

    int game_id;
    String id ,title,body;

    public Topic() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Topic(int game_id, String id, String title, String body) {
        this.game_id = game_id;
        this.id = id;
        this.title = title;
        this.body = body;
    }
}
