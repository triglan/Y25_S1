package com.example.a32imageswitcher;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.a32imageswitcher.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private int page = 1;

    private @NonNull ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
    }

    static final int[] RES_IDS = {
            R.mipmap.cat1, R.mipmap.cat2, R.mipmap.cat3, R.mipmap.cat4, R.mipmap.cat5,
    };

    public void onBtnPrev(View view) {
        page--;
        if (page<1) {
            page = RES_IDS.length;
        }
        binding.pageText.setText(page + " / " + RES_IDS.length);
        int resID = RES_IDS[page - 1];
        binding.mainImage.setImageResource(resID);
    }

    public void onBtnNext(View view) {
        page++;
        if (page > RES_IDS.length) {
            page = 1;
        }
        binding.pageText.setText(page + " / " + RES_IDS.length);
        int resID = RES_IDS[page - 1];
        binding.mainImage.setImageResource(resID);
    }
}