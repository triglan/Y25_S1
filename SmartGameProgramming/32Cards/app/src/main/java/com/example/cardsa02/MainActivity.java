package com.example.cardsa02;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cardsa02.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private @NonNull ActivityMainBinding ui;
    private ImageButton previousCardButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());
    }

    public void onBtnCard(View view) {
        Log.d("MainActivity", "Btn ID=" + view.getId());

        if(previousCardButton != null) {
            previousCardButton.setImageResource(R.mipmap.card_blue_back);
        }//previousCardButton이 클릭 전에는 없으니까 있을 때만 해야 프로그램이 안죽는다.

        ImageButton btn = (ImageButton) view;
        btn.setImageResource(R.mipmap.card_as);

        previousCardButton = btn;
    }

}