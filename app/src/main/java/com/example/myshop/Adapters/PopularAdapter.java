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
import com.example.myshop.Models.PopularModel;
import com.example.myshop.R;
import com.example.myshop.databinding.PopularItemsBinding;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    Context context;
    List<PopularModel> popularModelList;

    public PopularAdapter(Context context, List<PopularModel> popularModelList) {

        this.context = context;
        this.popularModelList = popularModelList;
    }

    @NonNull
    @Override
    public PopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_items,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder holder, int position) {

        PopularModel obj = popularModelList.get(position);

        holder.binding.popName.setText(obj.getName());
        holder.binding.popDes.setText(obj.getDescription());
        holder.binding.popDiscount.setText(obj.getDiscount());
        holder.binding.popRating.setText(obj.getRating());

        Glide.with(context).load(obj.getImg_url()).into(holder.binding.popImg);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ViewAllActivity.class);
            intent.putExtra("type", obj.getType());
            context.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return popularModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        PopularItemsBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = PopularItemsBinding.bind(itemView);
        }
    }
}
