package com.project.flinhtinh.apdater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.flinhtinh.R;
import com.project.flinhtinh.listener.OnCategoryListener;
import com.project.flinhtinh.model.Category;
import com.project.flinhtinh.viewholder.CategoryViewHolder;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    private List<Category> categoryList;
    private OnCategoryListener onCategoryListener;

    public CategoryAdapter(List<Category> categoryList, OnCategoryListener onCategoryListener) {
        this.categoryList = categoryList;
        this.onCategoryListener = onCategoryListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_category, parent, false);
        return new CategoryViewHolder(view, onCategoryListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);
        if(category == null){
            return;
        }
        holder.tvCategory.setText(category.getCategoryName());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
