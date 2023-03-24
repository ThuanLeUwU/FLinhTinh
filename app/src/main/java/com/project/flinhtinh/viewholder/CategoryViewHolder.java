package com.project.flinhtinh.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.flinhtinh.R;
import com.project.flinhtinh.listener.OnCategoryListener;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView tvCategory;
    public OnCategoryListener onCategoryListener;
    public CategoryViewHolder(@NonNull View itemView, OnCategoryListener onCategoryListener) {
        super(itemView);
        tvCategory = itemView.findViewById(R.id.tv_category);
        this.onCategoryListener = onCategoryListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onCategoryListener.onCategoryClick(getAdapterPosition());
    }
}
