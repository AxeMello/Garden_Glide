package com.axe.gardenglide;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class testing extends AppCompatActivity {

    private TextView textViewNumber;
    private Button buttonGenerate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testing);

        textViewNumber = findViewById(R.id.textViewNumber);
        buttonGenerate = findViewById(R.id.buttonGenerate);

        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomNumber = generateRandomNumber(65, 95);
                textViewNumber.setText(String.valueOf(randomNumber));
            }
        });
    }

    private int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
