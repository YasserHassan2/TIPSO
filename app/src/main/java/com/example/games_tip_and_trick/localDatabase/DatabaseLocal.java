package com.example.games_tip_and_trick.localDatabase;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.games_tip_and_trick.Models.Topic;


public class DatabaseLocal extends SQLiteOpenHelper {
    public Context context;
    private static DatabaseLocal Instance;
    public static final String DB_NAME = DatabaseInfo.DB_NAME;
    public static final int DB_VERSION = DatabaseInfo.DB_VERSION;

    public static final String TOPICS = DatabaseInfo.TOPICS;


    public static DatabaseLocal getInstance(Context context) {
        if (Instance == null)
            Instance = new DatabaseLocal(context.getApplicationContext(), DB_NAME);

        return Instance;
    }

    public DatabaseLocal(Context context, String dbName) {
        super(context, dbName, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        Log.e("DataBase", "DataBase.onCreate()");


        sqLiteDatabase.execSQL("create table  if not exists " + TOPICS +
                "(id_sql INTEGER PRIMARY KEY AUTOINCREMENT," +
                "parse_server_id TEXT, " +
                "game_id INTEGER, " +
                "title TEXT,"+
                "body TEXT) "
        );



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TOPICS);

        // Create tables again
        onCreate(db);
    }


    public void delete() {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TOPICS);
        db.close();
    }

    public Boolean insert_topics(Context context, Topic topic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("parse_server_id", topic.getId());
        contentValues.put("game_id", topic.getGame_id());
        contentValues.put("title", topic.getTitle());
        contentValues.put("body", topic.getBody());

        long insert = db.insert(TOPICS, null, contentValues);
        if (insert == -1) {
            Log.e("database", "insert >> false");
            long update = db.update(TOPICS, contentValues, "id=?", new String[]{String.valueOf(topic.getId())});
            if (update == -1) {
                Log.e("database", "update >> false");
                return false;
            } else {
                Log.e("database", "update >> true");
                return true;
            }
        } else {
            Log.e("database", "insert >> true");
            return true;
        }
    }





}
