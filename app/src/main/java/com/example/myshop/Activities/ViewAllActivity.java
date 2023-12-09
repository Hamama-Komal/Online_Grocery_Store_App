package com.example.myshop.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.myshop.Adapters.ViewAllAdapter;
import com.example.myshop.Models.ViewAllModel;
import com.example.myshop.R;
import com.example.myshop.databinding.ActivityViewAllBinding;
import com.example.myshop.databinding.ViewAllItemsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {
    ViewAllAdapter viewAllAdapter;
    List<ViewAllModel> viewAllModelList;

    FirebaseFirestore firestore;
    ActivityViewAllBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewAllBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.progressBar2.setVisibility(View.VISIBLE);
        binding.viewAllRecycler.setVisibility(View.GONE);
        firestore = FirebaseFirestore.getInstance();


        String type = getIntent().getStringExtra("type");

        binding.viewAllRecycler.setLayoutManager(new LinearLayoutManager(this));

        viewAllModelList = new ArrayList<>();
        viewAllAdapter = new ViewAllAdapter(this, viewAllModelList);
        binding.viewAllRecycler.setAdapter(viewAllAdapter);

        // Getting fruits
        if (type != null && type.equalsIgnoreCase("fruit")) {
            firestore.collection("AllProducts").whereEqualTo("type", "fruit").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                        binding.progressBar2.setVisibility(View.GONE);
                        binding.viewAllRecycler.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        // Getting vegetables
        if (type != null && type.equalsIgnoreCase("vegetable")) {
            firestore.collection("AllProducts").whereEqualTo("type", "vegetable").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                        binding.progressBar2.setVisibility(View.GONE);
                        binding.viewAllRecycler.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        // Getting dairy
        if (type != null && type.equalsIgnoreCase("dairy")) {
            firestore.collection("AllProducts").whereEqualTo("type", "dairy").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                        binding.progressBar2.setVisibility(View.GONE);
                        binding.viewAllRecycler.setVisibility(View.VISIBLE);
                    }
                }
            });
        }


        // Getting meat
        if (type != null && type.equalsIgnoreCase("meat")) {
            firestore.collection("AllProducts").whereEqualTo("type", "meat").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                        binding.progressBar2.setVisibility(View.GONE);
                        binding.viewAllRecycler.setVisibility(View.VISIBLE);
                    }
                }
            });
        }



        if(type == null){

            binding.progressBar2.setVisibility(View.GONE);
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
        }


    }
}