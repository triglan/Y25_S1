package com.example.firstapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.TestView); //TestView가 ID
        tv.setText("World");
        //실행하고 나서 Tukorea -> World로 런타임에 바꾼 것이다.

        //1번째 방식
        //Button btn = findViewById(R.id.pushmeButton);
        //btn.setOnClickListner(this); //OnClick에만 관심이 있다 근데 왜 this가 왜 onClick인걸 알았지?
        //2개 이상 있을 때 적합한 방법이 아니라고 한다.


        //2번째 방식
        //Button btn = findViewById(R.id.pushmeButton);
        //btn.setOnClickListener(my_member);

        //3번째 방법
//        Button btn = findViewById(R.id.pushmeButton);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextView tv = findViewById(R.id.TestView);
//                tv.setText("Way 3");
//            }
//        });


    }

    //4번째 방법 activity_main.xml에서 onClick 함수를 만들고 생성한 함수
    public void onBtnPushMe(View view) {
                  TextView tv = findViewById(R.id.TestView);
                tv.setText("Way 4");
    }


    // 2번째 방법
//    private View.OnClickListener my_member = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            TextView tv = findViewById(R.id.TestView);
//            tv.setText("Way 2");
//        }
//    };

//    @Override //1번째 방식
//    public void onClick(View v){
//        TextView tv = findViewById(R.id.TestView);
//        tv.setText("Button Clicked !!");
//    }
}

