package com.example.games_tip_and_trick.ui.TopicsLists;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.games_tip_and_trick.Adapters.CustomItemClickListener;
import com.example.games_tip_and_trick.Adapters.TopicAdapter;
import com.example.games_tip_and_trick.Loader.TopicsLoader;
import com.example.games_tip_and_trick.Models.Topic;
import com.example.games_tip_and_trick.R;
import com.example.games_tip_and_trick.ui.TopicDetails.TopicDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class TopicsListsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Topic>>  {
    RecyclerView recyclerView;
    TopicAdapter adapter;
    Intent intent;
    String TAG="tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics_lists);
        recyclerView = findViewById(R.id.topic_recycler);
        intent = getIntent();

        Log.d(TAG, "lists activity onCreate: game id " +  intent.getIntExtra("game_id", -1));

        getSupportLoaderManager().initLoader(0, null, this);

    }

    @NonNull
    @Override
    public Loader<List<Topic>> onCreateLoader(int id, @Nullable Bundle args) {
        Log.d(TAG, "onCreateLoader: loader");
        return new TopicsLoader(TopicsListsActivity.this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Topic>> loader, List<Topic> data) {
        Log.d(TAG, "onLoadFinished: ");

        final ArrayList<Topic> topicList = new ArrayList<Topic>();

        for (int i = 0; i < data.size(); i++) {

            if (data.get(i).getGame_id() == intent.getIntExtra("game_id", -1))
{
                topicList.add(data.get(i));


            } else {

            }

        }

        Log.d(TAG, "onLoadFinished: size " + topicList.size());


        adapter = new TopicAdapter(TopicsListsActivity.this,topicList);
        recyclerView.setLayoutManager(new LinearLayoutManager(TopicsListsActivity.this));
        recyclerView.setAdapter(adapter);
        adapter.setData(topicList);
        adapter.setOnItemClickListener(new CustomItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(TopicsListsActivity.this, TopicDetailsActivity.class);
                intent.putExtra("title",topicList.get(position).getTitle());
                intent.putExtra("body",topicList.get(position).getBody());
                startActivity(intent);
                
            }
        });
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Topic>> loader) {

    }
}
