package com.example.veloapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.veloapp.dao.CartDAO;
import com.example.veloapp.utils.model.BikeCart;

@Database(entities = {BikeCart.class},version = 1)
public abstract class CartDatabase extends RoomDatabase {

    public abstract CartDAO cartDAO();
    private static CartDatabase instance;

    public static synchronized CartDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),CartDatabase.class,"BikeDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return  instance;
    }
















}




