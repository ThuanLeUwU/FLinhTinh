package com.project.flinhtinh.apdater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.flinhtinh.R;
import com.project.flinhtinh.listener.OnButtonClickListener;
import com.project.flinhtinh.model.Cart;
import com.project.flinhtinh.model.OrderDetail;
import com.project.flinhtinh.viewholder.CartViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {
    private Cart cart;
    private Context context;
    private List<OrderDetail> list;
    private OnButtonClickListener onButtonClickListener;
    public CartAdapter(Context context, Cart cart, OnButtonClickListener onButtonClickListener) {
        this.context = context;
        this.cart = cart;
        this.list =  new ArrayList<>(this.cart.getCart().values());
        this.onButtonClickListener = onButtonClickListener;
    }

    public void setData(Cart cart){
        this.cart = cart;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        OrderDetail detail = list.get(position);
        if(detail == null){
            return;
        }
        Picasso.get().load(detail.getProduct().getImage()).into(holder.productImg);
        holder.productName.setText(detail.getProduct().getName());
        holder.productPrice.setText(String.valueOf(detail.getPrice() * detail.getQuantity()));
        holder.productQuantity.setText(String.valueOf(detail.getQuantity()));
        holder.bind(position, onButtonClickListener);

    }

    @Override
    public int getItemCount() {
        return cart.getCart().size();
    }

}
