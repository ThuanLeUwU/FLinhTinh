package com.project.flinhtinh.viewholder;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.flinhtinh.R;
import com.project.flinhtinh.listener.OnButtonClickListener;

public class CartViewHolder extends RecyclerView.ViewHolder {
    public ImageView productImg;
    public TextView productName, productPrice, productQuantity, remove;
    public Button increase, decrease;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        productImg = itemView.findViewById(R.id.product_image);
        productName = itemView.findViewById(R.id.product_name);
        productPrice = itemView.findViewById(R.id.product_price);
        productQuantity = itemView.findViewById(R.id.product_quantity);
        increase = itemView.findViewById(R.id.btn_increase);
        decrease = itemView.findViewById(R.id.btn_decrease);
        remove = itemView.findViewById(R.id.btn_delete);
    }

    public void bind(final int position, final OnButtonClickListener listener) {
        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST", "INCREASE CLICKED");
                listener.onButtonClick(getAdapterPosition(), "INCREASE");
            }
        });
        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST", "DECREASE CLICKED");
                listener.onButtonClick(getAdapterPosition(), "DECREASE");
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonClick(getAdapterPosition(), "REMOVE");
            }
        });
    }
}
