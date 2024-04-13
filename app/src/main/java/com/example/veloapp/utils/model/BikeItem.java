package com.example.veloapp.utils.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class BikeItem implements Parcelable {
    private String bikeName, bikeBrandName;
    private int bikeImage;
    private double bikePrice;

    public BikeItem(String bikeName, String bikeBrandName, int bikeImage, double bikePrice) {
        this.bikeName = bikeName;
        this.bikeBrandName = bikeBrandName;
        this.bikeImage = bikeImage;
        this.bikePrice = bikePrice;
    }

    protected BikeItem(Parcel in) {
        bikeName = in.readString();
        bikeBrandName = in.readString();
        bikeImage = in.readInt();
        bikePrice = in.readDouble();
    }

    public static final Creator<BikeItem> CREATOR = new Creator<BikeItem>() {
        @Override
        public BikeItem createFromParcel(Parcel in) {
            return new BikeItem(in);
        }

        @Override
        public BikeItem[] newArray(int size) {
            return new BikeItem[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(bikeName);
        dest.writeString(bikeBrandName);
        dest.writeInt(bikeImage);
        dest.writeDouble(bikePrice);
    }
}
