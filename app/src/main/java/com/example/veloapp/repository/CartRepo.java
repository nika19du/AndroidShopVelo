package com.example.veloapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.veloapp.dao.CartDAO;
import com.example.veloapp.database.CartDatabase;
import com.example.veloapp.utils.model.BikeCart;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CartRepo {

    private CartDAO cartDAO;
    private LiveData<List<BikeCart>> allCartItemsLiveData;
    private Executor executor = Executors.newSingleThreadExecutor();

    public CartRepo(Application application){
        cartDAO = CartDatabase.getInstance(application).cartDAO();
        allCartItemsLiveData = cartDAO.getAllCartItems();
    }

    public LiveData<List<BikeCart>> getAllCartItemsLiveData(){
        return  allCartItemsLiveData;
    }

    public void insertCartItem(BikeCart bikeCart){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.insertCartItem(bikeCart);
            }
        });
    }

    public void deleteCartItem(BikeCart bikeCart){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.deleteCartItem(bikeCart);
            }
        });
    }

    public void updateQuantity(int id, int quantity){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.updateQuantity(id,quantity);
            }
        });
    }

    public void updatePrice(int id, double price){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.updatePrice(id,price);
            }
        });
    }

    public void deleteAllCartItems(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.getAllCartItems();
            }
        });
    }







}
