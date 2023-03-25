package com.project.flinhtinh.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.flinhtinh.R;

public class CustomerViewHolder extends RecyclerView.ViewHolder {
    public TextView customerName, customerEmail, customerPhone, customerAddress, customerCity, customerStatus;
    public CustomerViewHolder(@NonNull View itemView) {
        super(itemView);
        customerName = itemView.findViewById(R.id.customer_name);
        customerEmail = itemView.findViewById(R.id.customer_email);
//        customerPhone = itemView.findViewById(R.id.customer_phone);
//        customerAddress = itemView.findViewById(R.id.customer_address);
//        customerCity = itemView.findViewById(R.id.customer_city);
        customerStatus = itemView.findViewById(R.id.customer_status);
    }
}
