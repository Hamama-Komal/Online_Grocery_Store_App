package com.example.myshop.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myshop.Activities.ViewAllActivity;
import com.example.myshop.Models.HomeCategory;
import com.example.myshop.R;
import com.example.myshop.databinding.HomeCatItemsBinding;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    Context context;
    List<HomeCategory> categoryList;

    public HomeAdapter(Context context, List<HomeCategory> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_cat_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {

        HomeCategory obj = categoryList.get(position);

        Glide.with(context).load(obj.getImg_url()).into(holder.binding.homeCatImg);
        holder.binding.catHomeName.setText(obj.getName());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ViewAllActivity.class);
            intent.putExtra("type", obj.getType());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        HomeCatItemsBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = HomeCatItemsBinding.bind(itemView);
        }
    }
}
