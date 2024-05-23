package com.axe.gardenglide;

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

        // Initialize views
        ImageView user = findViewById(R.id.user);
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
