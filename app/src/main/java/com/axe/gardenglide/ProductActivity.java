package com.axe.gardenglide;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        // Initialize views if necessary
//        TextView textView = findViewById(R.id.textView);

        ImageView product = findViewById(R.id.product);
        ImageView user = findViewById(R.id.user);
        ImageView background = findViewById(R.id.background);
        ImageView ggtit = findViewById(R.id.ggtit);

    }
}