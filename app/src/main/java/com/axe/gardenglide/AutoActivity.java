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
        ImageView logo = findViewById(R.id.ggtit);
        ImageView time = findViewById(R.id.time);
        ImageView date = findViewById(R.id.date);
        ImageView topA = findViewById(R.id.topA);

        TextView turnOffText = findViewById(R.id.turn_off_text);
        TextView wateringHistoryText = findViewById(R.id.watering_history_text);
        TextView systemStatusText = findViewById(R.id.system_status_text);
        TextView currentLevelText = findViewById(R.id.current_level_text);
        TextView activeText = findViewById(R.id.active_text);

        // Set Data using string resources
        turnOffText.setText(getString(R.string.turn_off));
        wateringHistoryText.setText(getString(R.string.watering_history));
        systemStatusText.setText(getString(R.string.system_status));
        currentLevelText.setText(getString(R.string.current_level));
        activeText.setText(getString(R.string.active));

        // Set any additional logic or event listeners here
    }
}
