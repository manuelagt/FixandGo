package com.example.manue.fixandgo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Request.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {


    public abstract RequestDao requestDao();
    private final static String NAME_DATABASE = "game_db";


    //Static instance
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {

        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context, AppDatabase.class, NAME_DATABASE)
                    .build();

        }

        return sInstance;

    }

}
