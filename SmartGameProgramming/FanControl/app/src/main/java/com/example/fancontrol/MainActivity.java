package com.example.fancontrol;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_layout);

        FanControlView fanControlView = findViewById(R.id.fanViewTop);
        FanControlView fanControlBottom = findViewById(R.id.fanViewBottom);

        findViewById(R.id.button0).setOnClickListener(v -> {
            fanControlView.setSelectedIndex(0);
            fanControlBottom.setSelectedIndex(0);
        });
        findViewById(R.id.button1).setOnClickListener(v -> {
            fanControlView.setSelectedIndex(1);
            fanControlBottom.setSelectedIndex(1);
        });
        findViewById(R.id.button2).setOnClickListener(v -> {
            fanControlView.setSelectedIndex(2);
            fanControlBottom.setSelectedIndex(2);
        });
        findViewById(R.id.button3).setOnClickListener(v -> {
            fanControlView.setSelectedIndex(3);
            fanControlBottom.setSelectedIndex(3);
        });
    }
}