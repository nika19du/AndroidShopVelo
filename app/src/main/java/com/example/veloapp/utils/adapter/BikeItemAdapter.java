package com.example.veloapp.utils.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Insert;

import com.example.veloapp.MainActivity;
import com.example.veloapp.R;
import com.example.veloapp.utils.model.BikeCart;
import com.example.veloapp.utils.model.BikeItem;
import com.example.veloapp.views.DetailedActivity;

import org.w3c.dom.Text;

import java.util.List;

public class BikeItemAdapter extends RecyclerView.Adapter<BikeItemAdapter.BikeItemViewHolder> {

    private List<BikeItem> bikeItemList;
    private BikeClickedListeners bikeClickedListeners;

    public BikeItemAdapter(BikeClickedListeners bikeClickedListeners){
        this.bikeClickedListeners = bikeClickedListeners;
    }
    public void setBikeItemList(List<BikeItem> bikeItemList){
        this.bikeItemList = bikeItemList;
    }

    @NonNull
    @Override
    public BikeItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_bike, parent, false);
        return new BikeItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BikeItemViewHolder holder, int position) {
        BikeItem bikeItem = bikeItemList.get(position);
        holder.bikeName.setText(bikeItem.getBikeName());
        holder.bikeBrandName.setText(bikeItem.getBikeBrandName());
        holder.bikePrice.setText(String.valueOf(bikeItem.getBikePrice()));
        holder.bikeImageView.setImageResource(bikeItem.getBikeImage());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bikeClickedListeners.onCardClicked(bikeItem);
            }
        });

        holder.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bikeClickedListeners.onAddToCartBtnClicked(bikeItem);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(bikeItemList == null){
            return 0;
        }else {
           return bikeItemList.size();
        }
    }

    public class BikeItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView bikeImageView , addToCartBtn;
        private TextView bikeName, bikeBrandName, bikePrice;
        private CardView cardView;
        public BikeItemViewHolder(@NonNull View itemView){
            super(itemView);

            cardView = itemView.findViewById(R.id.eachBikeCardView);
            addToCartBtn = itemView.findViewById(R.id.eachBikeAddToCartBtn);
            bikeName = itemView.findViewById(R.id.eachBikeName);
            bikeImageView = itemView.findViewById(R.id.eachBikeImg);
            bikeBrandName = itemView.findViewById(R.id.eachBikeBrandName);
            bikePrice = itemView.findViewById(R.id.eachBikePrice);

        }
    }

    public interface BikeClickedListeners{
        void onCardClicked(BikeItem bike);
        void onAddToCartBtnClicked(BikeItem bike);
    }

}
