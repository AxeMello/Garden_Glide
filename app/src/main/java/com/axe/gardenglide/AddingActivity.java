package com.axe.gardenglide;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);  // Ensure your XML file is named activity_main.xml

        // Initialize navigation bar icons
        ImageView navIcon1 = findViewById(R.id.nav_icon_1);
        ImageView navIcon2 = findViewById(R.id.nav_icon_2);
        ImageView navIcon3 = findViewById(R.id.nav_icon_3);
        ImageView navIcon4 = findViewById(R.id.nav_icon_4);

        // Set click listeners for navigation bar icons
        navIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddingActivity.this, "Home clicked", Toast.LENGTH_SHORT).show();
                // Add your code here to handle the home button click
            }
        });

        navIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddingActivity.this, "Camera clicked", Toast.LENGTH_SHORT).show();
                // Add your code here to handle the camera button click
            }
        });

        navIcon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddingActivity.this, "Cart clicked", Toast.LENGTH_SHORT).show();
                // Add your code here to handle the cart button click
            }
        });

        navIcon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddingActivity.this, "More options clicked", Toast.LENGTH_SHORT).show();
                // Add your code here to handle the more options button click
            }
        });
    }
}
