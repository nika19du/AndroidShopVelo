package com.example.veloapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.veloapp.utils.adapter.BikeItemAdapter;
import com.example.veloapp.utils.model.BikeCart;
import com.example.veloapp.utils.model.BikeItem;
import com.example.veloapp.viewmodel.CartViewModel;
import com.example.veloapp.views.CartActivity;
import com.example.veloapp.views.DetailedActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BikeItemAdapter.BikeClickedListeners {

    private RecyclerView recyclerView;
    private List<BikeItem> bikeItemList;
    private BikeItemAdapter bikeItemAdapter;
    private CartViewModel viewModel;
    private List<BikeCart> bikeCarts;
    private CoordinatorLayout coordinatorLayout;
    private ImageView cartImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeVariables();
        setUpList();

        bikeItemAdapter.setBikeItemList(bikeItemList);
        recyclerView.setAdapter(bikeItemAdapter);

        cartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        viewModel.getAllCartItems().observe(this, new Observer<List<BikeCart>>() {
            @Override
            public void onChanged(List<BikeCart> bikeCarts) {
                bikeCarts.addAll(bikeCarts);
            }
        });
    }

    private void setUpList(){
        bikeItemList.add(new BikeItem("Cube","Cube Attain",R.drawable.cube_a,950));
        bikeItemList.add(new BikeItem("Cube","Cube Carbon",R.drawable.cube_c,1650));
        bikeItemList.add(new BikeItem("S-Works","S-Works Attain",R.drawable.s_works,2550));
        bikeItemList.add(new BikeItem("Stevens","Stevens Prestige Gravel",R.drawable.stevens,2000));
        bikeItemList.add(new BikeItem("Scott Speedster","Scott Speedster",R.drawable.scott_s,1400));
        bikeItemList.add(new BikeItem("Merida","Merida Silex 700 Gravel/Shimano GRX/Carbon fork",R.drawable.merida,1500));
        bikeItemList.add(new BikeItem("Canyon","Canyon Ultimate CF SLX\n",R.drawable.canyoun,790));
        bikeItemList.add(new BikeItem("Twitter"," Twitter\n",R.drawable.twitter,1600));
        bikeItemList.add(new BikeItem("Calibre"," Calibre 28\n",R.drawable.calibre,940));

    }

    private void initializeVariables(){

        cartImageView = findViewById(R.id.cartImg);
        coordinatorLayout = findViewById(R.id.cordinatorLayout);
        bikeCarts = new ArrayList<>();
        viewModel = new ViewModelProvider(this).get(CartViewModel.class);

        bikeItemList = new ArrayList<>();
        recyclerView = findViewById(R.id.mainRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        bikeItemAdapter = new BikeItemAdapter(this);
    }

    @Override
    public void onCardClicked(BikeItem shoe) {
        Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
        intent.putExtra("bikeItem", shoe);
        startActivity(intent);
    }

    @Override
    public void onAddToCartBtnClicked(BikeItem bikeItem) {
        BikeCart bikeCart = new BikeCart();
        bikeCart.setBikeName(bikeItem.getBikeName());
        bikeCart.setBikeBrandName(bikeItem.getBikeBrandName());
        bikeCart.setBikePrice(bikeItem.getBikePrice());
        bikeCart.setBikeImage(bikeItem.getBikeImage());

        final int[] quantity = {1};
        final int[] id = new int[1];

        if (!bikeCarts.isEmpty()){
            for(int i = 0; i < bikeCarts.size(); i++){
                if(bikeCart.getBikeName().equals(bikeCarts.get(i).getBikeName())) {
                    quantity[0] = bikeCarts.get(i).getQuantity();
                    quantity[0]++;
                    id[0] = bikeCarts.get(i).getId();
                }
            }
        }

        if (quantity[0] == 1){
            bikeCart.setQuantity(quantity[0]);
            bikeCart.setTotalItemPrice(quantity[0] * bikeCart.getBikePrice());
            viewModel.insertCartItem(bikeCart);
        } else{
            viewModel.updateQuantity(id[0], quantity[0]);
            viewModel.updatePrice(id[0], quantity[0] * bikeCart.getBikePrice());
        }

        makeSnackBar("Item Added To Cart");

    }

    private void makeSnackBar(String message){
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT)
                .setAction("Go to Cart", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, CartActivity.class));
                    }
                }).show();
    }
}