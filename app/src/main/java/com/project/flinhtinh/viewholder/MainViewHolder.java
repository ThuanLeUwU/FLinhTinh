package com.project.flinhtinh.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.flinhtinh.R;
import com.project.flinhtinh.listener.OnProductListener;

public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView productTitle, productBrand, productQuantity;
    public ImageView productImg;
    public OnProductListener onProductListener;
    public MainViewHolder(@NonNull View itemView, OnProductListener onProductListener) {
        super(itemView);
        productTitle = itemView.findViewById(R.id.product_title);
        productBrand = itemView.findViewById(R.id.product_brand);
        productQuantity = itemView.findViewById(R.id.product_quantity);
        productImg = itemView.findViewById(R.id.img_product);
        this.onProductListener = onProductListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onProductListener.onProductClick(getAdapterPosition());
    }
}
