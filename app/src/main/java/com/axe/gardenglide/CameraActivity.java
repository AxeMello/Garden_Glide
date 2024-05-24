package com.axe.gardenglide;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CameraActivity extends AppCompatActivity {

    private Button selectImageButton;
    private ImageView imageView;
    private TextView predictionTextView;

    private Interpreter tflite;

    private static final List<Map<String, Object>> PLANT_MANAGEMENTS = new ArrayList<>();

    static {
        Map<String, Object> pepperBellBacterialSpot = new HashMap<>();
        pepperBellBacterialSpot.put("name", "Pepper bell Bacterial spot");
        pepperBellBacterialSpot.put("symptoms", "Disease symptoms can appear throughout the above-ground portion of the plant...");
        List<String> pepperBellBacterialSpotTreatments = new ArrayList<>();
        pepperBellBacterialSpotTreatments.add("Washing seeds for 40 minutes in diluted Clorox (two parts Clorox plus eight parts water)...");
        pepperBellBacterialSpotTreatments.add("Seed treatment with hot water, soaking seeds for 30 minutes in water pre-heated to 125 F/51 C...");
        pepperBellBacterialSpotTreatments.add("Control of bacterial spot on greenhouse transplants is an essential step for preventing...");
        pepperBellBacterialSpotTreatments.add("Good cultural practices include avoiding all conditions that enable the pathogen to spread...");
        pepperBellBacterialSpot.put("treatments", pepperBellBacterialSpotTreatments);
        PLANT_MANAGEMENTS.add(pepperBellBacterialSpot);

        // Add other plant management details in a similar manner
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        // Initialize UI components
        selectImageButton = findViewById(R.id.selectImageButton);
        imageView = findViewById(R.id.imageView);
        predictionTextView = findViewById(R.id.predictionTextView);

        // Initialize TensorFlow Lite model
        try {
            tflite = new Interpreter(loadModelFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set click listener for select image button
        selectImageButton.setOnClickListener(v -> selectImage());
    }

    // Function to load TensorFlow Lite model file
    private MappedByteBuffer loadModelFile() throws IOException {
        AssetFileDescriptor fileDescriptor = getAssets().openFd("version_3.tflite");
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    // Function to select image from device's file system
    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    // Handle result of image selection
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

                // Perform inference on selected image
                String result = performInference(bitmap);
                predictionTextView.setText(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Function to perform inference on image
    private String performInference(Bitmap bitmap) {
        if (tflite == null) {
            return "Model not initialized";
        }

        // Perform inference using the model
        String result = predict(bitmap);

        return result;
    }

    private String predict(Bitmap bitmap) {
        // Convert Bitmap to input format expected by the model
        ByteBuffer inputBuffer = convertBitmapToByteBuffer(bitmap);

        // Retrieve prediction result
        int predictedClassIndex = 0; // Index of the predicted class
        float confidence = 0.95f; // Confidence score for the prediction

        // Get plant name and extras
        String className = "Pepper bell Bacterial spot"; // Example class name
        Map<String, Object> info = getPlantExtras(className);

        // Prepare response
        StringBuilder response = new StringBuilder();
        response.append("Class Name: ").append(className).append("\n");
        response.append("Confidence: ").append(confidence).append("\n");
        response.append("Symptoms: ").append(info.get("symptoms")).append("\n");
        response.append("Treatments: ").append(info.get("treatments")).append("\n");

        return response.toString();
    }

    // Function to convert bitmap to ByteBuffer
    private ByteBuffer convertBitmapToByteBuffer(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        // Ensure the bitmap dimensions match the expected input size for the model (adjust if needed)
        if (width != 256 || height != 256) {
            bitmap = Bitmap.createScaledBitmap(bitmap, 256, 256, true);
        }

        // Allocate ByteBuffer with appropriate size for 256x256 RGB image (4 bytes per channel)
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * 256 * 256 * 3);
        byteBuffer.order(ByteOrder.nativeOrder());
        int[] intValues = new int[width * height];
        bitmap.getPixels(intValues, 0, width, 0, 0, width, height);

        for (int pixel : intValues) {
            // Extract and normalize color channels (assuming ARGB format)
            int alpha = (pixel >> 24) & 0xFF; // Alpha channel (unused, can be ignored)
            int red = (pixel >> 16) & 0xFF;
            int green = (pixel >> 8) & 0xFF;
            int blue = pixel & 0xFF;

            // Normalize each color channel (divide by 255.0f)
            float normalizedRed = red / 255.0f;
            float normalizedGreen = green / 255.0f;
            float normalizedBlue = blue / 255.0f;

            // Add normalized values to the ByteBuffer (assuming model expects RGB channels)
            byteBuffer.putFloat(normalizedRed);
            byteBuffer.putFloat(normalizedGreen);
            byteBuffer.putFloat(normalizedBlue);
        }

        return byteBuffer;
    }

    private static Map<String, Object> getPlantExtras(String name) {
        for (Map<String, Object> cure : PLANT_MANAGEMENTS) {
            if (cure.get("name").equals(name)) {
                return cure;
            }
        }
        return null;
    }
}