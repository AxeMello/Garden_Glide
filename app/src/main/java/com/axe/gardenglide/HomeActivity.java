package com.axe.gardenglide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity; // Ensure this import if using AppCompatActivity

public class HomeActivity extends AppCompatActivity { // or extends Activity if not using AppCompatActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView soil_moisture_tip = findViewById(R.id.soil_moisture_tip);
        soil_moisture_tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the SigninActivity
                Intent intent = new Intent(HomeActivity.this, AutoActivity.class);
                startActivity(intent);
            }
        });
        TextView moisture_level = findViewById(R.id.moisture_level);
        moisture_level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the SigninActivity
                Intent intent = new Intent(HomeActivity.this, AutoActivity.class);
                startActivity(intent);
            }
        });
        TextView fertilizer_level = findViewById(R.id.fertilizer_level);
        fertilizer_level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the SigninActivity
                Intent intent = new Intent(HomeActivity.this, AutoActivity.class);
                startActivity(intent);
            }
        });
        TextView fertilizer_tip = findViewById(R.id.fertilizer_tip);
        fertilizer_tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the SigninActivity
                Intent intent = new Intent(HomeActivity.this, AutoActivity.class);
                startActivity(intent);
            }
        });
        ImageView crop_recommended = findViewById(R.id.crop_recommended);
        crop_recommended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CroprecommendActivity.class);
                startActivity(intent);
            }
        });
        ImageView cam = findViewById(R.id.cam);
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ScanActivity.class);
                startActivity(intent);
            }
        });
        ImageView nav_icon_4 = findViewById(R.id.nav_icon_4);
        crop_recommended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MarketplaceActivity.class);
                startActivity(intent);
            }
        });


        // Ensure you have this layout file
        ImageView crop = findViewById(R.id.crop_recommended);
        crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CroprecommendActivity.class);
                startActivity(intent);
            }
        });

        ImageView cam = findViewById(R.id.cam);
        crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ScanActivity.class);
                startActivity(intent);
            }
        });

    }
}