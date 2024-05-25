package com.axe.gardenglide;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class test extends AppCompatActivity {

    private static final int REQUEST_CODE_SELECT_IMAGE = 1;
    private static final int MODEL_IMAGE_WIDTH = 224;
    private static final int MODEL_IMAGE_HEIGHT = 224;

    private ImageView imageView;
    private TextView comparisonResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        // Initialize views
        imageView = findViewById(R.id.imageView); // Use resource IDs with proper prefix
        comparisonResultTextView = findViewById(R.id.comparisonResultTextView);
        Button selectImageButton = findViewById(R.id.selectImageButton);

        // Set click listener for select image button
        selectImageButton.setOnClickListener(v -> selectImage());
    }

    // Function to select image from device's file system
    private void selectImage() {
        // Explicitly request permission to access device storage
        // (Consider adding a permission handling mechanism)
        Intent intent = new Intent(Intent.ACTION_PICK, // Use ACTION_PICK for gallery access
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            if (imageUri != null) {
                try {
                    // Load the input image
                    InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    Bitmap inputImage = BitmapFactory.decodeStream(imageStream);

                    // Display the image
                    imageView.setImageBitmap(inputImage);

                    // Check if the dimensions match the pre-trained model's expected dimensions
                    boolean isEqual = checkImageSize(inputImage);

                    // Print the result
                    if (isEqual) {
                        comparisonResultTextView.setText("The input image matches the pre-trained model's expected size.");
                    } else {
                        comparisonResultTextView.setText("The input image does not match the pre-trained model's expected size.");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    comparisonResultTextView.setText("Error loading the input image.");
                }
            }
        }
    }

    private boolean checkImageSize(Bitmap inputImage) {
        // Compare the width and height of the input image with the model's expected dimensions
        return inputImage.getWidth() == MODEL_IMAGE_WIDTH && inputImage.getHeight() == MODEL_IMAGE_HEIGHT;
    }
}
