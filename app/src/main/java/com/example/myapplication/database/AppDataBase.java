package com.example.myapplication.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.Entities.Libro;

@Database(entities = {Libro.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase sInstance;

    public abstract LibrosDao getLibrosDao();

    public static AppDataBase getInstance(Context context){

        if(sInstance == null){
            sInstance = Room.databaseBuilder(context, AppDataBase.class, "AppDataBase")
                    .allowMainThreadQueries()
                    .build();
        }

        return sInstance;
    }
}
