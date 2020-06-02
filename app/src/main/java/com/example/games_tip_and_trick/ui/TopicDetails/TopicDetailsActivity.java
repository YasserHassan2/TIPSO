package com.example.games_tip_and_trick.ui.TopicDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.games_tip_and_trick.R;

import org.w3c.dom.Text;

public class TopicDetailsActivity extends AppCompatActivity {
    TextView title,body;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_details);
        title = findViewById(R.id.title);
        body = findViewById(R.id.bodyy);
        intent = getIntent();



        title.setText(intent.getStringExtra("title"));
        body.setText(intent.getStringExtra("body"));




    }
}
