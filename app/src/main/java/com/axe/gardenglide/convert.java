package com.axe.gardenglide;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class convert extends AppCompatActivity {

    private Scaler scaler; // Assuming you have a Scaler class
    private LabelEncoder labelEncoder; // Assuming you have a LabelEncoder class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize your scaler and label encoder objects here
        scaler = loadScaler();
        labelEncoder = loadLabelEncoder();

        // Save the scaler parameters
        saveScalerParams(scaler);

        // Save the label classes
        saveLabelClasses(labelEncoder);
    }

    private void saveScalerParams(Scaler scaler) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonObject scalerParams = new JsonObject();
        JsonArray dataMin = new JsonArray();
        JsonArray dataMax = new JsonArray();

        for (double min : scaler.getDataMin()) {
            dataMin.add(min);
        }
        for (double max : scaler.getDataMax()) {
            dataMax.add(max);
        }

        scalerParams.add("min", dataMin);
        scalerParams.add("max", dataMax);

        String json = gson.toJson(scalerParams);

        try (FileOutputStream fos = openFileOutput("scaler_params.json", MODE_PRIVATE);
             OutputStreamWriter writer = new OutputStreamWriter(fos)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveLabelClasses(LabelEncoder labelEncoder) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonArray labelClasses = new JsonArray();
        for (String labelClass : labelEncoder.getClasses()) {
            labelClasses.add(labelClass);
        }

        String json = gson.toJson(labelClasses);

        try (FileOutputStream fos = openFileOutput("label_classes.json", MODE_PRIVATE);
             OutputStreamWriter writer = new OutputStreamWriter(fos)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Placeholder methods for loading scaler and label encoder
    private Scaler loadScaler() {
        // Load your scaler object
        return new Scaler();
    }

    private LabelEncoder loadLabelEncoder() {
        // Load your label encoder object
        return new LabelEncoder();
    }

    // Placeholder classes for Scaler and LabelEncoder
    class Scaler {
        public double[] getDataMin() {
            return new double[]{0.0}; // Replace with actual values
        }

        public double[] getDataMax() {
            return new double[]{1.0}; // Replace with actual values
        }
    }

    class LabelEncoder {
        public String[] getClasses() {
            return new String[]{"class1", "class2"}; // Replace with actual values
        }
    }
}
