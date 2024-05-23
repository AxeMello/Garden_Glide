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
        setContentView(R.layout.activity_home); // Ensure you have this layout file

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