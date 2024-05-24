package com.axe.gardenglide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MarketplaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplace);

        ImageView nav_icon_2 = findViewById(R.id.nav_icon_2);
        nav_icon_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MarketplaceActivity.this, ProductActivity.class);
                startActivity(intent);
            }
        });
        ImageView nav_icon_3 = findViewById(R.id.nav_icon_3);
        nav_icon_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MarketplaceActivity.this, AddproductActivity.class);
                startActivity(intent);
            }
        });

        ImageView nav_icon_1 = findViewById(R.id.nav_icon_1);
        nav_icon_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MarketplaceActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        ImageView user = findViewById(R.id.user);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MarketplaceActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });


        ImageView nav_icon_4 = findViewById(R.id.nav_icon_4);
        nav_icon_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MarketplaceActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        // Initialize views
        ImageView image4 = findViewById(R.id.image4);

        // Set click listener for user image
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle user image click logic here
            }
        });

        // Set click listener for image 4
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle image 4 click logic here
            }
        });
    }
}