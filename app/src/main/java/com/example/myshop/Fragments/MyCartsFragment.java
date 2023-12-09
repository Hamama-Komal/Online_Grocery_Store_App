package com.example.myshop.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myshop.Activities.PlacedOrderActivity;
import com.example.myshop.Adapters.MyCartAdapter;
import com.example.myshop.Models.MyCartModel;
import com.example.myshop.R;
import com.example.myshop.databinding.FragmentMyCartsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyCartsFragment extends Fragment {


    FragmentMyCartsBinding binding;
    List<MyCartModel> myCartModelList;
    MyCartAdapter cartAdapter;

    FirebaseFirestore firestore;

    FirebaseAuth auth;
    public MyCartsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyCartsBinding.inflate(inflater,container, false);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        binding.progressbar.setVisibility(View.VISIBLE);
        binding.recyclerView.setVisibility(View.GONE);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        myCartModelList = new ArrayList<>();
        cartAdapter = new MyCartAdapter(getActivity(), myCartModelList);
        binding.recyclerView.setAdapter(cartAdapter);

        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                       if(task.isSuccessful()){
                           for(DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){

                               String documentId = documentSnapshot.getId();

                               MyCartModel cartModel = documentSnapshot.toObject(MyCartModel.class);

                               cartModel.setDocumentId(documentId);

                               myCartModelList.add(cartModel);
                               cartAdapter.notifyDataSetChanged();

                               binding.progressbar.setVisibility(View.GONE);
                               binding.recyclerView.setVisibility(View.VISIBLE);

                           }

                           calculateTotalAmount(myCartModelList);
                       }


                    }
                });

        binding.buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), PlacedOrderActivity.class);
                intent.putExtra("itemList", (Serializable) myCartModelList);
                startActivity(intent);

            }
        });



        return binding.getRoot();
    }

    private void calculateTotalAmount(List<MyCartModel> myCartModelList) {

        int totalAmount = 0;
        for(MyCartModel myCartModel: myCartModelList){
            totalAmount += myCartModel.getTotalPrice();
        }

        binding.tvTotalPrice.setText("Total Price : $" + totalAmount);
    }
}