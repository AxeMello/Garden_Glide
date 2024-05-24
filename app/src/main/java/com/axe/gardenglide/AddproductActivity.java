package com.axe.gardenglide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AddproductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);


        ImageView home= findViewById(R.id.homee);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddproductActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        ImageView camera= findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddproductActivity.this, ScanActivity.class);
                startActivity(intent);
            }
        });


        ImageView cart= findViewById(R.id.cartin);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddproductActivity.this, MarketplaceActivity.class);
                startActivity(intent);
            }
        });

        ImageView sett= findViewById(R.id.nav_icon_4);
        sett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddproductActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });


        // Set click listener for image 4
        ImageView userb= findViewById(R.id.user);
        userb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddproductActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
