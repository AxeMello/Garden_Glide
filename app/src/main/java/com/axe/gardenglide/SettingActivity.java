package com.axe.gardenglide;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // Initialize your views
        TextView textMarkets = findViewById(R.id.textMarkets);
        TextView textBrand = findViewById(R.id.textBrand);
        TextView textDomains = findViewById(R.id.textDomains);
        TextView textAppsAndSalesChannels = findViewById(R.id.textAppsAndSalesChannels);
        TextView textCustomerEvents = findViewById(R.id.textCustomerEvents);
        TextView textCustomerData = findViewById(R.id.textCustomerData);
        TextView textSettings = findViewById(R.id.textSettings);
        TextView textFiles = findViewById(R.id.textFiles);
        TextView textGiftCards = findViewById(R.id.textGiftCards);
        TextView textNotification = findViewById(R.id.textNotification);
        TextView textLanguages = findViewById(R.id.textLanguages);
        TextView textCustomerPrivacy = findViewById(R.id.textCustomerPrivacy);
        TextView textPolicies = findViewById(R.id.textPolicies);
        TextView textStarActivityLog = findViewById(R.id.textStarActivityLog);
        TextView textGardenGlideAdmin = findViewById(R.id.textGardenGlideAdmin);

        ImageView user = findViewById(R.id.user);
        ImageView ggtit = findViewById(R.id.ggtit);
        ImageView image4 = findViewById(R.id.image4);
    }
}