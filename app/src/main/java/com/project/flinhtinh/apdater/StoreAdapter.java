package com.project.flinhtinh.apdater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.flinhtinh.R;
import com.project.flinhtinh.model.Store;
import com.project.flinhtinh.viewholder.StoreViewHolder;

import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreViewHolder> {
    private Context context;
    private List<Store> listStore;

    public StoreAdapter(Context context, List<Store> listStore) {
        this.context = context;
        this.listStore = listStore;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StoreViewHolder(LayoutInflater.from(context).inflate(R.layout.card_store, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        Store store = listStore.get(position);
        if(store == null){
            return;
        }
        holder.storeName.setText(store.getName());
        holder.storePhone.setText("Sđt: " + store.getPhone());
        holder.storeEmail.setText("Email: " + store.getEmail());
        holder.storeCity.setText("Thành phố: " + store.getCity());
        holder.storeAddress.setText("Địa chỉ: " + store.getAddress());
        holder.storeStatus.setText("Trạng thái: " + store.getStatus());
    }

    @Override
    public int getItemCount() {
        return listStore.size();
    }
}
