package com.axe.gardenglide;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String THINGSPEAK_URL = "https://api.thingspeak.com/channels/2559752/feeds.json?api_key=DIGU3A9AT7IW7Q4S&results=1";

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        generateAndDisplayValue();

        // Allow network operations on the main thread for simplicity
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Check network connectivity before fetching data
        if (isNetworkAvailable()) {
            fetchDataFromThingSpeak();
        } else {
            textView.setText("No internet connection available");
            Toast.makeText(this, "Please check your internet connection and try again", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void fetchDataFromThingSpeak() {
        try {
            URL url = new URL(THINGSPEAK_URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                String response = stringBuilder.toString();
                Log.d(TAG, "Response from ThingSpeak: " + response);  // Log the response for debugging
                parseAndDisplayData(response);
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            Log.e(TAG, "Error fetching data from ThingSpeak: " + e.getMessage());
            textView.setText("Error fetching data from ThingSpeak: " + e.getMessage());
        }
    }

    private void parseAndDisplayData(String response) {
        try {
            JSONObject json = new JSONObject(response);
            JSONArray feeds = json.getJSONArray("feeds");

            if (feeds.length() > 0) {
                JSONObject latestEntry = feeds.getJSONObject(0);
                String soilMoisture = latestEntry.optString("field1", "none"); // Use optString to return "none" if "field1" is not found
                String entryTime = latestEntry.optString("created_at", "none"); // Use optString to return "none" if "created_at" is not found
                Log.d(TAG, "Latest entry - Soil Moisture: " + soilMoisture + ", Time: " + entryTime); // Log the fetched values
                textView.setText("Soil Moisture Level: " + soilMoisture + "\nTime of Entry: " + entryTime);
            } else {
                Log.d(TAG, "No data found in ThingSpeak response.");
                textView.setText("No data found");
            }
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing data from ThingSpeak: " + e.getMessage());
            textView.setText("Error parsing data from ThingSpeak: " + e.getMessage());
        }
    }

    private void generateAndDisplayValue() {
        Random random = new Random();
        double randomValue = 75 + random.nextDouble() * 20;
        textView.setText(randomValue + "%");
    }
}
