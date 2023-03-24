package com.project.flinhtinh.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.flinhtinh.R;

public class StoreViewHolder extends RecyclerView.ViewHolder {
    public TextView storeName, storeEmail, storePhone, storeAddress, storeCity, storeStatus;
    public StoreViewHolder(@NonNull View itemView) {
        super(itemView);
        storeName = itemView.findViewById(R.id.store_name);
        storeEmail = itemView.findViewById(R.id.store_email);
        storePhone = itemView.findViewById(R.id.store_phone);
        storeAddress = itemView.findViewById(R.id.store_address);
        storeCity = itemView.findViewById(R.id.store_city);
        storeStatus = itemView.findViewById(R.id.store_status);
    }
}
