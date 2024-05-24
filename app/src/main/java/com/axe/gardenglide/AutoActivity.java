package com.axe.gardenglide;

import android.os.Bundle;
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

        // Initialize Views
        ImageView user = findViewById(R.id.user);
        ImageView time = findViewById(R.id.time);
        ImageView date = findViewById(R.id.date);
        ImageView topA = findViewById(R.id.topA);

        TextView turnOffText = findViewById(R.id.turn_off_text);
        TextView wateringHistoryText = findViewById(R.id.watering_history_text);

        // Set Data using string resources
        turnOffText.setText(getString(R.string.turn_off));

        // Set any additional logic or event listeners here
    }
}
