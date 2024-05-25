package com.axe.gardenglide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        ImageView nav_icon_1 = findViewById(R.id.homee);
        nav_icon_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        ImageView nav_icon_2 = findViewById(R.id.camee);
        nav_icon_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportActivity.this, ScanActivity.class);
                startActivity(intent);
            }
        });
        ImageView nav_icon_3 = findViewById(R.id.cartee);
        nav_icon_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportActivity.this, MarketplaceActivity.class);
                startActivity(intent);
            }
        });
        ImageView nav_icon_4= findViewById(R.id.dotee);
        nav_icon_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
        ImageView user = findViewById(R.id.user);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}