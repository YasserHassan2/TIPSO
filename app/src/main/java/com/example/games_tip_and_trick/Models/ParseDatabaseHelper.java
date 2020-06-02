package com.example.games_tip_and_trick.Models;

import android.content.Context;
import com.parse.FindCallback;
import com.parse.Parse;
public class ParseDatabaseHelper {

    Context mContext;


    public ParseDatabaseHelper(Context mContext) {
        this.mContext = mContext;
    }

    public void connectToDB() {

        //# database connection
        Parse.initialize(new Parse.Configuration.Builder(mContext)
                .applicationId("eOEjbpaURbjAtgURKzDRkJcbUEa96Jy5x3roYz5Z")
                .clientKey("ITT1gk03ZBZ9ELvkNjHZ3QZDTqIrYhn1bRiYdcZx")
                .server("https://parseapi.back4app.com")
                .enableLocalDataStore()
                .build()
        );


        //enable localdata store

//        Parse.enableLocalDatastore(mContext);
//        Parse.initialize(mContext);


    }



}