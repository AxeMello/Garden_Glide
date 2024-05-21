package com.axe.gardenglide;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ScanActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        // Set the top app bar title
        Toolbar topAppBar = findViewById(R.id.topAppBar);
        topAppBar.setTitle("Recent Scans");

        // Set images
        ImageView ggtit = findViewById(R.id.ggtit);
        ggtit.setImageResource(R.drawable.ggtit);

        ImageView user = findViewById(R.id.user);
        user.setImageResource(R.drawable.user);

        ImageView navIcon1 = findViewById(R.id.nav_icon_1);
        navIcon1.setImageResource(R.drawable.home);

        ImageView navIcon2 = findViewById(R.id.nav_icon_2);
        navIcon2.setImageResource(R.drawable.camera);

        ImageView navIcon3 = findViewById(R.id.nav_icon_3);
        navIcon3.setImageResource(R.drawable.cart);

        ImageView navIcon4 = findViewById(R.id.nav_icon_4);
        navIcon4.setImageResource(R.drawable.dot);

        ImageView image = findViewById(R.id.image);
        image.setImageResource(R.drawable.image);

        ImageView cam = findViewById(R.id.cam);
        cam.setImageResource(R.drawable.cam);

        ImageView reload = findViewById(R.id.reload);
        reload.setImageResource(R.drawable.reload);

        // You can set other images and texts similarly
    }
}
