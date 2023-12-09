package com.example.myshop.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myshop.Models.NavCatModel;
import com.example.myshop.R;
import com.example.myshop.databinding.NavCatItemBinding;

import java.util.List;

public class NavCategoryAdapter extends RecyclerView.Adapter<NavCategoryAdapter.ViewHolder> {

    Context context;
    List<NavCatModel> navCatModelList;

    public NavCategoryAdapter(Context context, List<NavCatModel> navCatModelList) {
        this.context = context;
        this.navCatModelList = navCatModelList;
    }

    @NonNull
    @Override
    public NavCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_cat_item,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NavCategoryAdapter.ViewHolder holder, int position) {

        NavCatModel obj = navCatModelList.get(position);

        Glide.with(context).load(obj.getImg_url()).into(holder.binding.catNavImg);

        holder.binding.navCatDescription.setText(obj.getDescription());
        holder.binding.navCatName.setText(obj.getName());
        holder.binding.navCatDiscount.setText(obj.getDiscount());

    }

    @Override
    public int getItemCount() {
        return navCatModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        NavCatItemBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = NavCatItemBinding.bind(itemView);
        }
    }
}
