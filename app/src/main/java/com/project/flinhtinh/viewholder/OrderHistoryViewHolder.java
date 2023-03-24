package com.project.flinhtinh.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.flinhtinh.R;

public class OrderHistoryViewHolder extends RecyclerView.ViewHolder {
    public TextView tv_order_id, tv_order_status, tv_order_quantity, tv_order_price, tv_order_date,tv_product_name;
    public ImageView img_product;

    public OrderHistoryViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_order_id = itemView.findViewById(R.id.order_id);
        tv_order_status = itemView.findViewById(R.id.order_status);
        tv_order_quantity = itemView.findViewById(R.id.order_quantity);
        tv_product_name = itemView.findViewById(R.id.product_name);
        tv_order_price = itemView.findViewById(R.id.order_price);
        tv_order_date = itemView.findViewById(R.id.order_date);
        img_product = itemView.findViewById(R.id.img_product);
    }
}
