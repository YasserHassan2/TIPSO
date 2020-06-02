package com.example.games_tip_and_trick.ui.Main;

import android.content.Context;

import com.example.games_tip_and_trick.Models.Game;
import com.example.games_tip_and_trick.R;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter {

    MainView view;
    Context context;

    public MainPresenter(MainView view) {
        this.view = view;
    }

    public List<Game> GetGamesFromDatabase()
    {
        Game game = new Game(1,"https://4.bp.blogspot.com/-Q3Gj5Lz_O7w/XHeteWOEohI/AAAAAAAAEuU/TVW57iw6rv0eKNoTzDcfgz16NmoM4fMYACLcBGAs/s1600/pubg-mobile-huong-dan-su-dung-cac-phu-kien-hieu-qua-nhat1542009065.jpg","PUBG Mobile");
        Game game2 = new Game(2,"https://www.androidnature.com/wp-content/uploads/2019/09/IMG_20190907_115548.jpg","Call Of Duty Mobile");
        Game game3 = new Game(3,"https://lh3.googleusercontent.com/proxy/X3iQjKBVSHLNiKIUHWAKASujPp6g9FtgkaG04udZh5chqYOhBOmZS73yvEh1w7ksUh9OzRiYlkHWyH2gk8UXW73QJCzc056Gcoj9PSXqmBeJUba2d8WQ1R6foEWZVreoRAoahWAMG2hlSS4d3uRqN0k1Gw8fynJc","Clash Of Clans");
        Game game4 = new Game(4,"https://1.bp.blogspot.com/-8AkkY2U8p_k/XPUxPldltWI/AAAAAAAADZk/WQzetbFvN5wWPu8gpdlvioGVwycSJBSOgCLcBGAs/s1600/Free-fire-wallpaper%2B%25288%2529.jpg","Free Fire");
        Game game5 = new Game(5,"https://image.winudf.com/v2/image/Y29tLmd1cHB5aW8ubWluZWNyYWZ0aGRfc2NyZWVuXzE2XzE1MjgwMzMxNzdfMDMx/screen-16.jpg?fakeurl=1&type=.jpg","Minecraft");

        List<Game> gamesList = new ArrayList<Game>();
        gamesList.add(game);
        gamesList.add(game2);
        gamesList.add(game3);
        gamesList.add(game4);
        gamesList.add(game5);

        return gamesList;
    }
    public void getGames(){
        view.onGetGames(GetGamesFromDatabase());
    }

}
