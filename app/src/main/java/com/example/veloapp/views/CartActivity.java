package com.example.veloapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.veloapp.R;
import com.example.veloapp.utils.adapter.CartAdapter;
import com.example.veloapp.utils.model.BikeCart;
import com.example.veloapp.viewmodel.CartViewModel;

import java.util.List;

public class CartActivity extends AppCompatActivity implements CartAdapter.CartClickedListeners {

    private RecyclerView recyclerView;
    private CartViewModel cartViewModel;
    private TextView totalCartPrice, textView;
    private AppCompatButton checkoutBtn;
    private CardView cardView;
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        cartViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(CartViewModel.class);

        initializeVariables();

        cartViewModel.getAllCartItems().observe(this, new Observer<List<BikeCart>>() {
            @Override
            public void onChanged(List<BikeCart> bikeCarts) {
                double price = 0;
                cartAdapter.setBikeCartList(bikeCarts);
                for (int i = 0; i< bikeCarts.size(); i++) {
                    price = price + bikeCarts.get(i).getTotalItemPrice();
                }

                totalCartPrice.setText(String.valueOf(price));
            }
        });

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartViewModel.deleteAllCartItems();
                textView.setVisibility(View.INVISIBLE);
                checkoutBtn.setVisibility(View.INVISIBLE);
                totalCartPrice.setVisibility(View.INVISIBLE);
                cardView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initializeVariables(){

        cartAdapter = new CartAdapter(this);

        textView = findViewById(R.id.textView2);
        cardView = findViewById(R.id.cartActivityCardView);
        totalCartPrice = findViewById(R.id.cartActivityTotalPriceTv);
        checkoutBtn = findViewById(R.id.cartActivityCheckoutBtn);
        recyclerView = findViewById(R.id.cartRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(cartAdapter);
    }

    @Override
    public void onDeleteClicked(BikeCart bikeCart) {
        cartViewModel.deleteCartItem(bikeCart);
    }

    @Override
    public void onPlusClicked(BikeCart bikeCart) {
        int quantity = bikeCart.getQuantity() + 1;
        cartViewModel.updateQuantity(bikeCart.getId(),quantity);
        cartViewModel.updatePrice(bikeCart.getId(), quantity * bikeCart.getBikePrice());
        cartAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMinusClicked(BikeCart bikeCart) {
        int quantity = bikeCart.getQuantity() - 1;
        if (quantity != 0) {
            cartViewModel.updateQuantity(bikeCart.getId(),quantity);
            cartViewModel.updatePrice(bikeCart.getId(), quantity * bikeCart.getBikePrice());
            cartAdapter.notifyDataSetChanged();
        } else{
            cartViewModel.deleteCartItem(bikeCart);
        }

    }
}