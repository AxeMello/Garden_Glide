package com.axe.gardenglide;

import android.app.Activity;
import android.content.Intent;
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
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class CameraActivity extends AppCompatActivity {

    private Button selectImageButton;
    private ImageView imageView;
    private TextView predictionTextView;

    private Interpreter tflite;

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
        FileInputStream inputStream = null;
        FileChannel fileChannel = null;
        try {
            android.content.res.AssetFileDescriptor fileDescriptor = getAssets().openFd("model.tflite");
            inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
            fileChannel = inputStream.getChannel();
            long startOffset = fileDescriptor.getStartOffset();
            long declaredLength = fileDescriptor.getDeclaredLength();
            return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (fileChannel != null) {
                fileChannel.close();
            }
        }
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
                FileInputStream inputStream = (FileInputStream) getContentResolver().openInputStream(data.getData());
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

                // Perform inference on selected image
                String result = performInference(bitmap);
                predictionTextView.setText("Prediction: " + result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Function to perform inference on image
    private String performInference(Bitmap bitmap) {
        // Resize the bitmap to the input size of the model
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, 224, 224, true);

        // Convert bitmap to ByteBuffer
        ByteBuffer inputBuffer = convertBitmapToByteBuffer(resizedBitmap);

        // Create an output array to hold the inference results
        float[][] output = new float[1][1];

        // Run inference
        tflite.run(inputBuffer, output);

        // Interpret the result (assuming binary classification)
        float prediction = output[0][0];
        return prediction > 0.5 ? "Class A" : "Class B";
    }

    private ByteBuffer convertBitmapToByteBuffer(Bitmap bitmap) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * 224 * 224 * 3);
        byteBuffer.order(ByteOrder.nativeOrder());
        int[] intValues = new int[224 * 224];
        bitmap.getPixels(intValues, 0, 224, 0, 0, 224, 224);
        for (int pixel : intValues) {
            byteBuffer.putFloat(((pixel >> 16) & 0xFF) / 255.0f);
            byteBuffer.putFloat(((pixel >> 8) & 0xFF) / 255.0f);
            byteBuffer.putFloat((pixel & 0xFF) / 255.0f);
        }
        return byteBuffer;
    }
}
