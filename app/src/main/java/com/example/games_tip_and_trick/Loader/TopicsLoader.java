package com.example.games_tip_and_trick.Loader;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.games_tip_and_trick.Models.Topic;
import com.example.games_tip_and_trick.localDatabase.DatabaseLocal;

import java.util.ArrayList;
import java.util.List;

public class TopicsLoader extends AbstractQueryLoader<List<Topic>> {

    public Context _context;

    public TopicsLoader(Context context) {
        super(context);
        this._context = context;
    }

    @Override
    public List<Topic> loadInBackground() {

        DatabaseLocal mydb =  DatabaseLocal.getInstance(getContext());
        SQLiteDatabase db = mydb.getReadableDatabase();
        List<Topic> results = null;
        Cursor GroupCursor = null;

        try {

            GroupCursor = db.rawQuery("SELECT * FROM topics ", null);

            if (GroupCursor != null && GroupCursor.moveToFirst()) {
                results = new ArrayList<>();
                do {
                    Topic list = new Topic();
                    list.setId(GroupCursor.getString(1));
                    list.setGame_id(GroupCursor.getInt(2));

                    list.setTitle(GroupCursor.getString(3));

                    list.setBody(GroupCursor.getString(4));

                    results.add(list);

                } while (GroupCursor.moveToNext());
            }
        } finally {
            if (GroupCursor != null) {
                GroupCursor.close();
            }
        }

        return results;
    }
}