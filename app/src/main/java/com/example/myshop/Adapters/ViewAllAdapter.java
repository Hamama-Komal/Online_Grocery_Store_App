package com.example.myshop.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myshop.Activities.DetailedActivity;
import com.example.myshop.Activities.ViewAllActivity;
import com.example.myshop.Models.ViewAllModel;
import com.example.myshop.R;
import com.example.myshop.databinding.ViewAllItemsBinding;

import java.util.List;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {

    Context context;
    List<ViewAllModel> list;

    public ViewAllAdapter(Context context, List<ViewAllModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewAllAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_items, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllAdapter.ViewHolder holder, int position) {


        ViewAllModel obj = list.get(position);

        holder.binding.viewAllName.setText(obj.getName());
        holder.binding.viewAllDescription.setText(obj.getDescription());
        holder.binding.viewRating.setText(obj.getRating());
        holder.binding.tvPrice.setText(String.valueOf(obj.getPrice()));

        Glide.with(context).load(obj.getImg_url()).into(holder.binding.viewAllImg);

        holder.itemView.setOnClickListener(view -> {

            Intent intent = new Intent(context, DetailedActivity.class);
            intent.putExtra("detail",obj);
            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ViewAllItemsBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = ViewAllItemsBinding.bind(itemView);
        }
    }
}
