package com.example.veloapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.veloapp.repository.CartRepo;
import com.example.veloapp.utils.model.BikeCart;

import java.util.List;

public class CartViewModel extends AndroidViewModel {

    private CartRepo cartRepo;
    public CartViewModel(@NonNull Application application) {
        super(application);
        cartRepo = new CartRepo(application);
    }

    public LiveData<List<BikeCart>> getAllCartItems(){
        return cartRepo.getAllCartItemsLiveData();
    }
    public void insertCartItem(BikeCart bikeCart){
        cartRepo.insertCartItem(bikeCart);
    }

    public void updateQuantity(int id, int quantity){
        cartRepo.updateQuantity(id,quantity);
    }

    public void updatePrice(int id,double price){
        cartRepo.updatePrice(id,price);
    }

    public void deleteCartItem(BikeCart bikeCart){
        cartRepo.deleteCartItem(bikeCart);
    }

    public void deleteAllCartItems(){
        cartRepo.deleteAllCartItems();
    }

}
