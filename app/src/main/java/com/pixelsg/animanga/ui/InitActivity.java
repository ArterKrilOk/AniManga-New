package com.pixelsg.animanga.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.pixelsg.animanga.R;
import com.pixelsg.animanga.databinding.ActivityInitBinding;
import com.pixelsg.animanga.viewmodels.InitActivityViewModel;

public class InitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityInitBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_init);

        InitActivityViewModel viewModel = new ViewModelProvider(this).get(InitActivityViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        viewModel.getConfig().observe(this, viewModel::configsEvent);
        binding.initRetryButton.setOnClickListener((v) -> viewModel.getConfig().observe(this, viewModel::configsEvent));
    }
}