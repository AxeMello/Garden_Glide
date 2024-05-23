package com.axe.gardenglide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ImageView nav_icon_2 = findViewById(R.id.nav_icon_2);
        nav_icon_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, ProductActivity.class);
                startActivity(intent);
            }
        });
        ImageView nav_icon_3 = findViewById(R.id.nav_icon_3);
        nav_icon_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, AddproductActivity.class);
                startActivity(intent);
            }
        });
        ImageView nav_icon_4 = findViewById(R.id.nav_icon_4);
        nav_icon_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        // Initialize your views
        TextView textMarkets = findViewById(R.id.textMarkets);
        TextView textBrand = findViewById(R.id.textBrand);
        TextView textDomains = findViewById(R.id.textDomains);
        TextView textCustomerData = findViewById(R.id.textCustomerData);
        TextView textSettings = findViewById(R.id.textSettings);
        TextView textNotification = findViewById(R.id.textNotification);
        TextView textLanguages = findViewById(R.id.textLanguages);
        TextView textCustomerPrivacy = findViewById(R.id.textCustomerPrivacy);
        TextView textPolicies = findViewById(R.id.textPolicies);
        TextView textGardenGlideAdmin = findViewById(R.id.textGardenGlideAdmin);

        ImageView user = findViewById(R.id.user);
        ImageView image4 = findViewById(R.id.image4);
}
}