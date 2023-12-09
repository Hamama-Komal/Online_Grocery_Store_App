package com.example.myshop.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myshop.Models.ViewAllModel;
import com.example.myshop.R;
import com.example.myshop.databinding.ActivityDetailedBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {

    ActivityDetailedBinding binding;
    ViewAllModel viewAllModel = null;

    int totalQuantity = 1;
    int totalPrice = 0;

    FirebaseFirestore firestore;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof ViewAllModel) {

            viewAllModel = (ViewAllModel) object;

            if (viewAllModel != null) {
                Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(binding.detailedImg);
                binding.detailedRating.setText(viewAllModel.getRating());
                binding.tvDescription.setText(viewAllModel.getDescription());
                binding.detailedPrice.setText("Price :$" + String.valueOf(viewAllModel.getPrice()));

                totalPrice = viewAllModel.getPrice() * totalQuantity;


            }

        }


        binding.addItem.setOnClickListener(view -> {

            if (totalQuantity < 10) {
                totalQuantity++;
                binding.quantity.setText(String.valueOf(totalQuantity));
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
        });


        binding.removeItem.setOnClickListener(view -> {

            if (totalQuantity > 1) {
                totalQuantity--;
                binding.quantity.setText(String.valueOf(totalQuantity));
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
        });


        binding.addToCart.setOnClickListener(view -> {

            addedToCart();
        });


    }

    private void addedToCart() {
        String saveCurrentDate, saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM,DD,YYYY");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String, Object> cartMap = new HashMap<>();

        cartMap.put("productName", viewAllModel.getName());
        cartMap.put("productPrice", binding.detailedPrice.getText().toString());
        cartMap.put("currentDate", saveCurrentDate);
        cartMap.put("currentTime", saveCurrentTime);
        cartMap.put("totalQuantity", binding.quantity.getText().toString());
        cartMap.put("totalPrice", totalPrice);


        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(DetailedActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });


    }
}