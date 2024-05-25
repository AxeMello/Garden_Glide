package com.axe.gardenglide;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class iot extends AppCompatActivity {

    // ThingSpeak settings
    private static final String CHANNEL_ID = "2559752";  // Your Channel ID
    private static final String READ_API_KEY = "DIGU3A9AT7IW7Q4S";  // Your Read API Key
    private static final String THINGSPEAK_URL = "https://api.thingspeak.com/channels/" + CHANNEL_ID + "/feeds.json";

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // Replace 'activity_layout' with your actual layout file

        textViewResult = findViewById(R.id.textViewResult);

        // Call AsyncTask to fetch data from ThingSpeak
        new FetchDataFromThingSpeakTask().execute();
    }

    private class FetchDataFromThingSpeakTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String jsonResponse = "";
            try {
                String url = THINGSPEAK_URL + "?api_key=" + READ_API_KEY + "&results=1";
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                // Send a GET request
                con.setRequestMethod("GET");

                int responseCode = con.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) { // Success
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    // Store JSON response
                    jsonResponse = response.toString();
                } else {
                    jsonResponse = "Error fetching data from ThingSpeak. HTTP Response Code: " + responseCode;
                }
            } catch (IOException e) {
                jsonResponse = "Error fetching data from ThingSpeak: " + e.getMessage();
            }
            return jsonResponse;
        }

        @Override
        protected void onPostExecute(String result) {
            displayData(result);
        }
    }

    private void displayData(String jsonResponse) {
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray feedsArray = jsonObject.getJSONArray("feeds");

            if (feedsArray.length() > 0) {
                JSONObject latestEntry = feedsArray.getJSONObject(0);

                // Extract data you want to display
                String field1Value = latestEntry.getString("field1");

                // Display data in TextView
                textViewResult.setText(field1Value + "%");
            } else {
                textViewResult.setText("0%");
            }
        } catch (JSONException e) {
            textViewResult.setText("-");
            e.printStackTrace();
        }
    }
}
