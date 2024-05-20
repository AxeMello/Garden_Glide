package com.axe.gardenglide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MarketplaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplace);

        // Initialize views
        TextView welcomeText = findViewById(R.id.welcome_text);
        TextView homeText = findViewById(R.id.home_text);
        TextView storeText = findViewById(R.id.store_text);
        ImageView userImage = findViewById(R.id.user_image);
        ImageView finalLogo = findViewById(R.id.final_logo);
        ImageView image4 = findViewById(R.id.image_4);

        // Set click listener for user image
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle user image click logic here
            }
        });

        // Set click listener for final logo
        finalLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle final logo click logic here
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
