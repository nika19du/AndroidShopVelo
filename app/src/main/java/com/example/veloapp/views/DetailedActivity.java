package com.example.veloapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.veloapp.R;
import com.example.veloapp.utils.model.BikeCart;
import com.example.veloapp.utils.model.BikeItem;
import com.example.veloapp.viewmodel.CartViewModel;

import java.util.ArrayList;
import java.util.List;

public class DetailedActivity extends AppCompatActivity {

    private ImageView bikeImageView;
    private TextView bikeName,bikeBrandName,bikePrice;
    private AppCompatButton addToCartBtn;
    private BikeItem bike;
    private CartViewModel cartViewModel;
    private List<BikeCart> bikeCartList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        bike = getIntent().getParcelableExtra("bikeItem");
        initializeVariables();

        cartViewModel.getAllCartItems().observe(this, new Observer<List<BikeCart>>() {
            @Override
            public void onChanged(List<BikeCart> bikeCarts) {
                bikeCartList.addAll(bikeCarts);
            }
        });

        if (bike != null){
            setDataToWidgets();
        }

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertToRoom();
            }
        });

    }

    private void insertToRoom() {
        BikeCart bikeCart = new BikeCart();
        bikeCart.setBikeName(bike.getBikeName());
        bikeCart.setBikeBrandName(bike.getBikeBrandName());
        bikeCart.setBikePrice(bike.getBikePrice());
        bikeCart.setBikeImage(bike.getBikeImage());

        final int[] quantity = {1};
        final int[] id = new int[1];

        if (!bikeCartList.isEmpty()){
            for(int i = 0; i < bikeCartList.size(); i++){
                if(bikeCart.getBikeName().equals(bikeCartList.get(i).getBikeName())) {
                    quantity[0] = bikeCartList.get(i).getQuantity();
                    quantity[0]++;
                    id[0] = bikeCartList.get(i).getId();
                }
            }
        }

        if (quantity[0] == 1){
            bikeCart.setQuantity(quantity[0]);
            bikeCart.setTotalItemPrice(quantity[0] * bikeCart.getBikePrice());
            cartViewModel.insertCartItem(bikeCart);
        } else{
            cartViewModel.updateQuantity(id[0], quantity[0]);
            cartViewModel.updatePrice(id[0], quantity[0] * bikeCart.getBikePrice());
        }

        startActivity(new Intent(DetailedActivity.this, CartActivity.class));
    }

    private void setDataToWidgets() {
        bikeName.setText(bike.getBikeName());
        bikeBrandName.setText(bike.getBikeBrandName());
        bikePrice.setText(String.valueOf(bike.getBikePrice()));
        bikeImageView.setImageResource(bike.getBikeImage());
    }

    private void initializeVariables(){
        bikeCartList = new ArrayList<>();

        bikeImageView = findViewById(R.id.detailActivityBikeImg);
        bikeName = findViewById(R.id.detailActivityBikeName);
        bikeBrandName = findViewById(R.id.detailActivityBikeBrandName);
        bikePrice = findViewById(R.id.detailActivityBikePrice);
        addToCartBtn = findViewById(R.id.detailActivityAddToCartBtn);

        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
    }
}