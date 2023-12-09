package com.example.myshop.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myshop.R;
import com.example.myshop.databinding.FragmentNewProductsBinding;


public class NewProductsFragment extends Fragment {

    FragmentNewProductsBinding binding;
    public NewProductsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewProductsBinding.inflate(inflater, container, false);




        return  binding.getRoot();
    }
}