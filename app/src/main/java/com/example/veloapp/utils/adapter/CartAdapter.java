package com.example.veloapp.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veloapp.R;
import com.example.veloapp.utils.model.BikeCart;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private CartClickedListeners cartClickedListeners;
    private List<BikeCart> bikeCartList;

    public CartAdapter(CartClickedListeners cartClickedListeners){
        this.cartClickedListeners = cartClickedListeners;
    }

    public void setBikeCartList(List<BikeCart> cartList){
        this.bikeCartList = cartList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_cart_item,parent,false);
        return  new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        BikeCart bikeCart = bikeCartList.get(position);
        //holder.bikeImageView.setImageResource(bikeCart.getBikeImage());
        holder.bikeName.setText(bikeCart.getBikeName());
        holder.bikeBrandName.setText(bikeCart.getBikeBrandName());
        holder.bikeQuantity.setText(bikeCart.getQuantity() + "");
        holder.bikePrice.setText(bikeCart.getTotalItemPrice() + "");

        holder.deleteBikeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onDeleteClicked(bikeCart);
            }
        });

        holder.addQuantityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onPlusClicked(bikeCart);
            }
        });

        holder.minusQuantityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onMinusClicked(bikeCart);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (bikeCartList==null){
            return 0;
        }else{
            return bikeCartList.size();
        }
    }

    public class CartViewHolder extends  RecyclerView.ViewHolder
    {
        private TextView bikeName, bikeBrandName,bikePrice,bikeQuantity;
        private ImageView deleteBikeBtn;
        private ImageView bikeImageView;
        private ImageButton addQuantityBtn, minusQuantityBtn;

        public CartViewHolder(@NonNull View itemView){
            super(itemView);

            bikeName = itemView.findViewById(R.id.eachCartItemName);
            bikeBrandName = itemView.findViewById(R.id.eachCartItemBrandNameTv);
            bikePrice = itemView.findViewById(R.id.eachCartItemPriceTv);
            deleteBikeBtn = itemView.findViewById(R.id.eachCartItemDeleteBtn);
            bikeQuantity = itemView.findViewById(R.id.eachCartItemQuantityTV);
           // bikeImageView = itemView.findViewById(R.id.eachBikeImg);
            addQuantityBtn = itemView.findViewById(R.id.eachCartItemAddQuantityBtn);
            minusQuantityBtn = itemView.findViewById(R.id.eachCartItemMinusQuantityBtn);
        }

    }
    public interface CartClickedListeners{
        void onDeleteClicked(BikeCart bikeCart);
        void onPlusClicked(BikeCart bikeCart);
        void onMinusClicked(BikeCart bikeCart);
    }
}
