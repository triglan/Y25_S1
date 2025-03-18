package com.example.a32imageswitcher;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private int page;
    private @NonNull ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    static final int[] RES_IDS = {
            R.mipmap.cat1,R.mipmap.cat2,R.mipmap.cat3,R.mipmap.cat4,R.mipmap.cat5
    };

    public void onBtnPrev(View view) {
        binding.pageTextView.setText("2 / 5");
        page = 2;
        int resId = RES_IDS[page -1];
        binding.mainImageView.setImageResource(R.mipmap.cat2);
    }

    public void onBtnNext(View view) {
        binding.pageTextView.setText("3 / 5");
        binding.mainImageView.setImageResource(R.mipmap.cat3);
    }
}