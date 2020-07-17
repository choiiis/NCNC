package com.kokonut.NCNC;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CarWashInfoActivity extends AppCompatActivity {
    Toolbar tb;
    ImageView ivBack, ivOnButton, ivOnButton2, ivOnButton3;

    public static final int sub = 1001;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_wash_info);
        initView();

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivityForResult(intent, sub);
            }
        });

    }

    protected void onPause() {
        super.onPause();
        finish();
    }


    void initView(){
        ivBack = findViewById(R.id.car_wash_info_back_arrow);
    }
}
