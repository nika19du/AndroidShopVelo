package com.example.veloapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.veloapp.utils.model.BikeCart;

import java.util.List;

@Dao
public interface CartDAO {

    @Insert
    void insertCartItem(BikeCart bikeCart);

    @Query("SELECT * FROM bike_table")
    LiveData<List<BikeCart>> getAllCartItems();

    @Delete
    void deleteCartItem(BikeCart bikeCart);

    @Query("UPDATE bike_table SET quantity=:quantity WHERE id=:id")
    void updateQuantity(int id, int quantity);

    @Query("UPDATE bike_table SET totalItemPrice=:totalItemPrice WHERE id=:id")
    void updatePrice(int id, double totalItemPrice);

    @Query("DELETE FROM bike_table")
    void deleteAllItems();
}
