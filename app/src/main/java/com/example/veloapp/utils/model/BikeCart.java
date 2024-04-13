package com.example.veloapp.utils.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bike_table")
public class BikeCart {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String bikeName, bikeBrandName;
    private int bikeImage;
    private double bikePrice;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalItemPrice() {
        return totalItemPrice;
    }

    public void setTotalItemPrice(double totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }

    private int quantity;
    private double totalItemPrice;
    public String getBikeName() {
        return bikeName;
    }

    public void setBikeName(String bikeName) {
        this.bikeName = bikeName;
    }

    public String getBikeBrandName() {
        return bikeBrandName;
    }

    public void setBikeBrandName(String bikeBrandName) {
        this.bikeBrandName = bikeBrandName;
    }

    public int getBikeImage() {
        return bikeImage;
    }

    public void setBikeImage(int bikeImage) {
        this.bikeImage = bikeImage;
    }

    public double getBikePrice() {
        return bikePrice;
    }

    public void setBikePrice(double bikePrice) {
        this.bikePrice = bikePrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
