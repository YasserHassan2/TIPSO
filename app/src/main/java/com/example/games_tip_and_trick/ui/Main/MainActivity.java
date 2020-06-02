package com.example.games_tip_and_trick.ui.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.games_tip_and_trick.Adapters.CustomItemClickListener;
import com.example.games_tip_and_trick.Adapters.GamesAdapter;
import com.example.games_tip_and_trick.Models.Game;
import com.example.games_tip_and_trick.R;
import com.example.games_tip_and_trick.ui.TopicsLists.TopicsListsActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {
    MainPresenter mainPresenter;
    RecyclerView recyclerView;
    GamesAdapter gamesAdapter;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mainPresenter = new MainPresenter(this);

        recyclerView = findViewById(R.id.game_recycler);
        mainPresenter.getGames();


    }

    @Override
    public void onGetGames(final List<Game> gameList) {
        gamesAdapter = new GamesAdapter(this,gameList);
        //horizontal recyclerView orign
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(gamesAdapter);

        gamesAdapter.setOnItemClickListener(new CustomItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                intent = new Intent(MainActivity.this, TopicsListsActivity.class);
                intent.putExtra("game_id",gameList.get(position).getId());
                String TAG = "tag";
                Log.d(TAG, "onItemClick: game id " + gameList.get(position).getId());
                startActivity(intent);

            }
        });
    }
}
