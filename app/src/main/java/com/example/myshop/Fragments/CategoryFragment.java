package com.example.myshop.Fragments;

import static com.example.myshop.databinding.NavHeaderBinding.inflate;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myshop.Adapters.NavCategoryAdapter;
import com.example.myshop.Adapters.PopularAdapter;
import com.example.myshop.Models.NavCatModel;
import com.example.myshop.Models.PopularModel;
import com.example.myshop.R;
import com.example.myshop.databinding.FragmentCategoryBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import io.grpc.BinaryLog;


public class CategoryFragment extends Fragment {


    FragmentCategoryBinding binding;
    NavCategoryAdapter adapter;
    List<NavCatModel> list;
    FirebaseFirestore firestore;

    public CategoryFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCategoryBinding.inflate(inflater, container, false);


        firestore = FirebaseFirestore.getInstance();

        binding.progressbar.setVisibility(View.VISIBLE);


        // Popular Items Setup
        binding.catRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        list = new ArrayList<>();
        adapter = new NavCategoryAdapter(getActivity(), list);
        binding.catRec.setAdapter(adapter);


        firestore.collection("NavCategory").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {


                        NavCatModel navCatModel = document.toObject(NavCatModel.class);
                        list.add(navCatModel);
                        adapter.notifyDataSetChanged();


                        binding.progressbar.setVisibility(View.GONE);


                    }
                } else {

                    Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });


        return binding.getRoot();
    }
}