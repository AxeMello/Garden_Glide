package com.axe.gardenglide;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReportActivity extends AppCompatActivity {

    private ImageView image4, user, plantImage, ghumidity, gtemperature, navIcon1, navIcon2, navIcon3, navIcon4;
    private TextView plantName, plantStatus, last30Days, humidityPercentage, humidityLabel, twLabel1D, twLabelTW, twLabel1M, whatToKnow1, whatToKnow2, whatToKnow3, whatToKnow4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        // Initialize views
        image4 = findViewById(R.id.image4);
        user = findViewById(R.id.user);
        plantImage = findViewById(R.id.plantImage);
        ghumidity = findViewById(R.id.ghumidity);
        gtemperature = findViewById(R.id.Gtemperature);
        navIcon1 = findViewById(R.id.nav_icon_1);
        navIcon2 = findViewById(R.id.nav_icon_2);
        navIcon3 = findViewById(R.id.nav_icon_3);
        navIcon4 = findViewById(R.id.nav_icon_4);

        plantName = findViewById(R.id.plantName);
        plantStatus = findViewById(R.id.plantStatus);
        last30Days = findViewById(R.id.last30Days);
        humidityPercentage = findViewById(R.id.humidityPercentage);
        humidityLabel = findViewById(R.id.humidityLabel);

        // Example of setting text programmatically
        plantName.setText("Monstera");
        plantStatus.setText("Your plant is looking good");
        last30Days.setText("Last 30 Days");
        humidityPercentage.setText("60%");
        humidityLabel.setText("Humidity");
        whatToKnow1.setText("Keep soil damp, but not too wet");
        whatToKnow2.setText("Prefers bright, indirect light");
        whatToKnow3.setText("Fertilize once a month in spring and summer");
        whatToKnow4.setText("What to Know");
    }
}
