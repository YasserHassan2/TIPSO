package com.example.games_tip_and_trick.ui.Splash;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.games_tip_and_trick.Models.DatabaseVersion;
import com.example.games_tip_and_trick.Models.Topic;
import com.example.games_tip_and_trick.R;
import com.example.games_tip_and_trick.localDatabase.DatabaseInterface;
import com.example.games_tip_and_trick.localDatabase.DatabaseLocal;
import com.example.games_tip_and_trick.localDatabase.DatabaseManager;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class SplashPresenter extends AppCompatActivity {

    private static final String TAG = "Splash_Presenter";
    static Context context;
    static DatabaseManager databaseManager;
    static DatabaseLocal mydb;
    static ProgressDialog Dialog;
    static Dialog updateDialog;



    public SplashPresenter(Context context) {
        this.context = context;
        databaseManager = new DatabaseManager(context);
    }

    public static Boolean checkUpdate(int last_databaseVersion, DatabaseInterface databaseInterface) {


        String local_databaseVersion = String.valueOf(databaseManager.getDatabaseVersion().getDb_version());
        Log.d(TAG, "checkUpdate:  local databse version " + local_databaseVersion + " last database " + last_databaseVersion);


        if ((local_databaseVersion != null) && (Integer.parseInt(local_databaseVersion) >= -1)) {

            if (Integer.parseInt(local_databaseVersion) != last_databaseVersion) {
                Log.d(TAG, "checkUpdate:  local databse version " + local_databaseVersion + " last database " + last_databaseVersion);


                databaseManager.updateDatabaseVersion(new DatabaseVersion(last_databaseVersion));
                db_request(context, databaseInterface);


                return true;
            } else {
                Log.d(TAG, "checkUpdate: false");
                return false;
            }
        } else {
            databaseManager.updateDatabaseVersion(new DatabaseVersion(last_databaseVersion));
            db_request(context, databaseInterface);
            return true;
        }


    }


    public static void db_request(final Context context, final @Nullable DatabaseInterface callbacks) {
        Log.d(TAG, "database is loading");

        updateDialog = new Dialog(context);
        updateDialog.setContentView(R.layout.updating_dialog);
        updateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        updateDialog.show();


//        Dialog = new ProgressDialog(context);
//
//        Dialog.setMessage("Loading Components");
//        Dialog.show();
        callbacks.onStart();

        mydb = DatabaseLocal.getInstance(context);
        final ArrayList<Topic> topicList = new ArrayList<>();




        try {
            mydb.delete();
            final ParseQuery<ParseObject> query = ParseQuery.getQuery("Topics");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, final ParseException e) {
                    if (e == null) {
                        for (ParseObject o : objects) {

                            Log.d(TAG, "done: loading --- > " + o.getString("name"));

                            String topic_id = o.getObjectId();
                            int topicGameId = o.getInt("game_id");
                            String topic_title = o.getString("title");
                            String topic_body = o.getString("body");



                            Topic topic = new Topic(topicGameId,topic_id,topic_title,topic_body);
                            topicList.add(topic);

                        }
                        for (int i = 0; i < topicList.size(); i++) {
                            Log.d(TAG, "run: " + topicList.get(i).getTitle());
                            mydb.insert_topics(context, new Topic(
                                    topicList.get(i).getGame_id(),
                                    topicList.get(i).getId(),
                                    topicList.get(i).getTitle(),
                                    topicList.get(i).getBody()));


                        }

                    } else {
                        Toast.makeText(context, "Please Try Again later.", Toast.LENGTH_SHORT).show();
                    }
                }
            });







            if (callbacks != null) {

                callbacks.onSuccess(true);



            }
        } catch (final Exception ex) {

            Log.d(TAG, "run: ex " + ex.getLocalizedMessage());
        }

        new Thread() {
            @Override
            public void run() {
                Looper.prepare();//Call looper.prepare()

            }
        }.start();


    }

    public static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
}

