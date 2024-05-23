package com.axe.gardenglide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AutoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto);

        ImageView nav_icon_2 = findViewById(R.id.nav_icon_2);
        nav_icon_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AutoActivity.this, ProductActivity.class);
                startActivity(intent);
            }
        });
        ImageView nav_icon_3 = findViewById(R.id.nav_icon_3);
        nav_icon_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AutoActivity.this, AddproductActivity.class);
                startActivity(intent);
            }
        });
        ImageView nav_icon_4 = findViewById(R.id.nav_icon_4);
        nav_icon_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AutoActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        // Initialize Views
        ImageView user = findViewById(R.id.user);
        ImageView time = findViewById(R.id.time);
        ImageView date = findViewById(R.id.date);
        ImageView topA = findViewById(R.id.topA);

        TextView turnOffText = findViewById(R.id.turn_off_text);
        TextView wateringHistoryText = findViewById(R.id.watering_history_text);

        // Set Data using string resources
        turnOffText.setText(getString(R.string.turn_off));
        wateringHistoryText.setText(getString(R.string.watering_history));

    }
}