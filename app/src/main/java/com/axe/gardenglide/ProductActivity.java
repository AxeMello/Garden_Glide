package com.axe.gardenglide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        ImageView background = findViewById(R.id.background);
        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, AddproductActivity.class);
                startActivity(intent);
            }
        });
        ImageView nav_icon_1 = findViewById(R.id.nav_icon_1);
        nav_icon_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this,
                        HomeActivity.class);
                startActivity(intent);
            }
        });
        ImageView nav_icon_3 = findViewById(R.id.nav_icon_3);
        nav_icon_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, AddproductActivity.class);
                startActivity(intent);
            }
        });
        ImageView nav_icon_4 = findViewById(R.id.nav_icon_4);
        nav_icon_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
        ImageView user = findViewById(R.id.user);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}