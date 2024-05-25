package com.axe.gardenglide;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class HomeActivity extends AppCompatActivity {
    private TextView textViewNumber;
    private final Handler handler = new Handler();
    private final int MIN_VALUE = 65;
    private final int MAX_VALUE = 95;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textViewNumber = findViewById(R.id.textViewNumber);

        // Start updating random number every 5 seconds
        handler.postDelayed(updateValue, 5000);

        ImageView user = findViewById(R.id.user);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });

        ImageView cropR = findViewById(R.id.cropee);
        cropR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CroprecommendActivity.class);
                startActivity(intent);
            }
        });

        ImageView cam = findViewById(R.id.camee);
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ScanActivity.class);
                startActivity(intent);
            }
        });

        ImageView analysis = findViewById(R.id.reportee);
        analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });

        ImageView userb = findViewById(R.id.user);
        userb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        ImageView nav_icon_4 = findViewById(R.id.cartee);
        nav_icon_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MarketplaceActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove the callback when the activity is destroyed to prevent memory leaks
        handler.removeCallbacks(updateValue);
    }

    private final Runnable updateValue = new Runnable() {
        @Override
        public void run() {
            int value = Value(MIN_VALUE, MAX_VALUE);
            textViewNumber.setText(String.valueOf(value) + "%");
            handler.postDelayed(this, 5000); // Run again after 5 seconds
        }
    };

    private int Value(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}