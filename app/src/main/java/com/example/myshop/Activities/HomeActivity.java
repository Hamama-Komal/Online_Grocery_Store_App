package com.example.myshop.Activities;

import static com.example.myshop.R.*;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myshop.Fragments.CategoryFragment;
import com.example.myshop.Fragments.HomeFragment;
import com.example.myshop.Fragments.MyCartsFragment;
import com.example.myshop.Fragments.MyOrderFragment;
import com.example.myshop.Fragments.NewProductsFragment;
import com.example.myshop.Fragments.OffersFragment;
import com.example.myshop.Fragments.ProfileFragment;
import com.example.myshop.Models.UserModel;
import com.example.myshop.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;
import io.github.florent37.shapeofview.shapes.CircleView;

public class HomeActivity extends AppCompatActivity {

    com.example.myshop.databinding.ActivityHomeBinding binding;

    Toolbar toolbar;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.myshop.databinding.ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseDatabase =FirebaseDatabase.getInstance();
        // Setup Toolbar
        toolbar = findViewById(id.home_toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,binding.drawerLayout, toolbar, string.openDrawer, string.closeDrawer);
        binding.drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

         loadFragments(new HomeFragment());
/*


                    // Code is working



        TextView headerName = findViewById(id.nav_header_name);
        TextView headerEmail = findViewById(id.nav_header_email);
        // CircleImageView headerImg = findViewById(id.nav_header_img);


        firebaseDatabase.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel userModel = snapshot.getValue(UserModel.class);

                        headerName.setText(userModel.getName());
                        headerEmail.setText(userModel.getEmail());

                       // Glide.with(HomeActivity.this).load(userModel.getProfileImg()).placeholder(drawable.profile).into(headerImg);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
*/


        binding.navDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if(id == R.id.profile){
                    loadFragments(new ProfileFragment());

                }
                else if (id == R.id.category) {

                    loadFragments(new CategoryFragment());
                }
                else if (id == R.id.offers) {
                    loadFragments(new OffersFragment());
                    
                }
                else if (id == R.id.products) {
                    loadFragments(new NewProductsFragment());

                }
                else if (id == R.id.orders) {
                    loadFragments(new MyOrderFragment());

                }
                else if (id == R.id.home) {
                    loadFragments(new HomeFragment());

                }
                else  {
                    loadFragments(new MyCartsFragment());
                }

                binding.drawerLayout.closeDrawer(GravityCompat.START);


                return true;
            }});





    }

    /** @noinspection deprecation*/
    @Override
    public void onBackPressed() {

        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void loadFragments(Fragment fragment) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.add(id.home_content, fragment);
            fragmentTransaction.commit();
        }

}