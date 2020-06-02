package com.example.games_tip_and_trick.ui.Splash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.games_tip_and_trick.Models.ParseDatabaseHelper;
import com.example.games_tip_and_trick.R;
import com.example.games_tip_and_trick.localDatabase.DatabaseInterface;
import com.example.games_tip_and_trick.localDatabase.DatabaseManager;
import com.example.games_tip_and_trick.ui.Main.MainActivity;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";
    private final int SPLASH_DISPLAY_LENGTH = 10000;
    ImageView iv_viewMe_logo;
    int databseVersion;
    DatabaseManager databaseManager;
    SplashPresenter splashPresenter;
    ParseDatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        iv_viewMe_logo = findViewById(R.id.iv_logo);

        databaseHelper = new ParseDatabaseHelper(SplashActivity.this);
        databaseHelper.connectToDB();
        databaseManager = new DatabaseManager(this);
        splashPresenter = new SplashPresenter(SplashActivity.this);

        YoYo.with(Techniques.Landing)
                .duration(3000)
                .repeat(0)
                .playOn(iv_viewMe_logo);
        getDataConfigFromDatabase();

    }

    public void getDataConfigFromDatabase() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("database_config");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, final ParseException e) {
                if (e == null) {
                    for (ParseObject o : objects) {
                        databseVersion = o.getInt("database_version");
                    }
                    if (SplashPresenter.checkUpdate(databseVersion, new DatabaseInterface() {
                        @Override
                        public void onStart() {
                            Log.d(TAG, "onStart: ");

                            Log.d(TAG, "done: installing database");

                        }

                        @Override
                        public void onSuccess(@NonNull Boolean value) {
                            Log.d(TAG, "onSuccess: sql successed");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    /* Create an Intent that will start the Menu-Activity. */
                                    IntentToMainActivity();
                                }
                            }, SPLASH_DISPLAY_LENGTH);


                        }

                        @Override
                        public void onError(@NonNull Throwable throwable) {


                        }
                    })) ;
                    else {
                        Log.d(TAG, "done: database is already installed");
                        new Handler().postDelayed(new Runnable(){
                            @Override
                            public void run() {
                                /* Create an Intent that will start the Menu-Activity. */
                                IntentToMainActivity();
                            }
                        }, 4000);

                    }
                } else {

                    Toast.makeText(SplashActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();

                }

                }



        });
    }

    private void IntentToMainActivity() {
        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}