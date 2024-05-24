package com.axe.gardenglide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CroprecommendActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_croprecommend);

        ImageView nav_icon_1 = findViewById(R.id.nav_icon_1);
        nav_icon_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CroprecommendActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        ImageView nav_icon_2 = findViewById(R.id.nav_icon_2);
        nav_icon_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CroprecommendActivity.this, MarketplaceActivity.class);
                startActivity(intent);
            }
        });
        ImageView nav_icon_3 = findViewById(R.id.nav_icon_3);
        nav_icon_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CroprecommendActivity.this, AddproductActivity.class);
                startActivity(intent);
            }
        });
        ImageView nav_icon_4= findViewById(R.id.nav_icon_4);
        nav_icon_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CroprecommendActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
        ImageView user = findViewById(R.id.user);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CroprecommendActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        try {
            cropClassifier = new cropClassifier(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        float[] input = {120, 50, 70, 27, 82.0f, 226.655537f}; // Example input
        String result = cropClassifier.classify(input);

        TextView resultTextView = findViewById(R.id.resultTextView);
        resultTextView.setText("Predicted Crop: " + result);

    }
}