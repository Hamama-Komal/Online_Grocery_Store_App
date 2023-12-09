package com.example.myshop.Adapters;

import static com.example.myshop.Adapters.RecommendedAdapter.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myshop.Models.RecommendedModel;
import com.example.myshop.R;
import com.example.myshop.databinding.RecommendedItemBinding;

import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.ViewHolder> {

    Context context;
    List<RecommendedModel> recommendedModelList;

    public RecommendedAdapter(Context context, List<RecommendedModel> recommendedModelList) {
        this.context = context;
        this.recommendedModelList = recommendedModelList;
    }

    @NonNull
    @Override
    public RecommendedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecommendedAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_item,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedAdapter.ViewHolder holder, int position) {

        RecommendedModel obj = recommendedModelList.get(position);

        holder.binding.tvName.setText(obj.getName());
        holder.binding.recDec.setText(obj.getDescription());
        holder.binding.recRating.setText(obj.getRating());



        Glide.with(context).load(obj.getImg_url()).into(holder.binding.recImg);

    }

    @Override
    public int getItemCount() {
        return recommendedModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RecommendedItemBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = RecommendedItemBinding.bind(itemView);
        }
    }
}
