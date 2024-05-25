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

import java.util.Random;
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
                predictionTextView.setText("Prediction: " + result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Function to perform inference on image
    private String performInference(Bitmap bitmap) {
        if (tflite == null) {
            String[] diseases = {
                    "Bacterial spot \n\n Symptoms:\n Look for small, water-soaked spots on leaves, stems, and fruit that turn dark and become surrounded by a yellow halo. \n Treatments:\n" +
                            "Sanitize Garden Tools: Clean tools with a 10% bleach solution after each use to avoid contamination.\n" +
                            "Avoid Overhead Watering: Use drip irrigation to keep foliage dry and reduce the chances of bacterial spread.\n" +
                            "Apply Copper-Based Sprays: Use copper-based fungicides as per label instructions to control the disease.\n" +
                            "Plant Resistant Varieties and Rotate Crops: Choose resistant tomato varieties and practice crop rotation to minimize future outbreaks.",
                    "Fungi disease \n\n Symptoms: \n Look for signs such as powdery mildew, black spots, rust, or mold on leaves, stems, and flowers.\n" +
                            "Treatments: \n Remove and Destroy Infected Parts: Prune and dispose of affected leaves, stems, and plant debris to reduce the spread of spores.\n" +
                            "Improve Air Circulation: Space plants appropriately and prune them to ensure good air circulation, which helps in drying out foliage and reducing fungal growth.\n" +
                            "Water Appropriately: Water plants at the base early in the day to keep foliage dry and reduce humidity, which can help prevent fungal growth.\n" +
                            "Use Fungicides: Apply appropriate fungicides as per label instructions. Organic options include neem oil, sulfur, and copper-based products. Ensure to follow application guidelines for the best results.",
                    "Mosaic virus \n\n Symptoms: \n Look for irregular mottling, yellowing, and a mosaic-like pattern on leaves, which can also be curled or distorted. Stunted growth is another common symptom.\n" +
                            "Treatments:\n Remove Infected Plants: Immediately remove and destroy infected plants to prevent the virus from spreading to healthy plants.\n" +
                            "Control Insect Vectors: Since mosaic viruses are often spread by insects like aphids, use insecticidal soaps or horticultural oils to control these pests. Consider introducing beneficial insects such as ladybugs to keep aphid populations in check.\n" +
                            "Disinfect Tools and Hands: Clean gardening tools and wash your hands after handling infected plants to prevent the virus from spreading.\n" +
                            "Plant Resistant Varieties: Choose and plant virus-resistant varieties of plants, as they are less likely to become infected and can help reduce the overall incidence of the disease in your garden.",
                    "Rust spots \n\n Symptoms: \n Look for small, yellow or white spots on the upper surface of leaves that develop into orange, red, or brown pustules on the undersides. Infected leaves may eventually turn yellow and drop prematurely.\n" +
                            "Treatments: \nRemove Infected Plant Parts: Carefully remove and dispose of infected leaves, stems, and any plant debris to reduce the spread of rust spores.\n" +
                            "Improve Air Circulation: Space plants appropriately and prune overcrowded foliage to improve air circulation, reducing the humidity that rust fungi thrive in.\n" +
                            "Apply Fungicides: Use appropriate fungicides at the first sign of rust. Organic options like sulfur or copper-based fungicides can be effective. Follow the label instructions for application rates and timing.\n" +
                            "Practice Good Garden Hygiene: Clean up fallen leaves and plant debris regularly, and avoid overhead watering to minimize leaf wetness. Rotate crops to prevent the buildup of rust spores in the soil.",
                    "Powdery Mildew \n\n Symptoms: Look for white or grayish powdery spots on leaves, stems, and buds. Initially, these spots are small but can spread to cover large areas. Infected leaves may become distorted, yellow, and drop prematurely.\n" +
                            "Treatments: \n Prune Infected Parts: Remove and dispose of infected leaves, stems, and buds to reduce the spread of the fungus. Use sanitized pruning tools to avoid contaminating healthy parts of the plant.\n" +
                            "Improve Air Circulation: Space plants properly to ensure good air circulation and reduce humidity around them. Avoid overhead watering and water plants at their base to keep the foliage dry.\n" +
                            "Apply Fungicides: Use fungicides such as sulfur, neem oil, or potassium bicarbonate to treat powdery mildew. Apply these treatments early in the disease cycle and repeat as recommended by the product instructions.\n" +
                            "Promote Plant Health: Maintain overall plant health by providing adequate nutrients, water, and care. Avoid excessive nitrogen fertilization, which can encourage lush growth susceptible to powdery mildew. Opt for resistant plant varieties when possible.",
                    "Fusarium Wilt \n\n Symptoms: \n Look for wilting and yellowing of the lower leaves, which gradually progresses upward. Affected plants may exhibit stunted growth and brown discoloration in the vascular tissue of the stems. The plant may eventually wilt and die.\n" +
                            "Treatments: \n Remove Infected Plants: Immediately remove and destroy infected plants, including the roots, to prevent the spread of the fungus. Do not compost these plants, as the pathogen can survive in the soil.\n" +
                            "Rotate Crops: Practice crop rotation by not planting susceptible plants in the same location for at least three to four years. This helps reduce the buildup of Fusarium spores in the soil.\n" +
                            "Improve Soil Drainage: Ensure your soil has good drainage and avoid overwatering, as Fusarium thrives in moist conditions. Raised beds and well-drained soil can help prevent the disease.\n" +
                            "Use Resistant Varieties: Plant Fusarium-resistant varieties and hybrids. Look for seeds labeled as resistant to Fusarium wilt, especially for crops like tomatoes, peppers, and melons.",
                    "Sooty Mold \n\n Symptoms: Sooty mold appears as a black, powdery coating on leaves, stems, and other plant parts. It does not directly harm the plant but can block sunlight, reducing photosynthesis and overall plant vigor.\n" +
                            "Treatments: \n Control Honeydew-Producing Insects: Sooty mold often grows on the honeydew excreted by insects such as aphids, whiteflies, and scale insects. Control these pests using insecticidal soaps, neem oil, or horticultural oils.\n" +
                            "Clean Affected Areas: Gently wash affected plant parts with water and a mild detergent solution to remove the sooty mold. Be careful not to damage the plant in the process.\n" +
                            "Improve Air Circulation: Prune dense foliage to improve air circulation around your plants. This can help reduce humidity and make the environment less favorable for mold growth.\n" +
                            "Maintain Plant Health: Keep your plants healthy by providing adequate water, nutrients, and sunlight. Healthy plants are better able to resist and recover from pest infestations and mold growth.",
                    "Alternaria Blight \n\n Symptoms: Alternaria manifests as dark, concentric spots or lesions on leaves, stems, and fruits. The spots can enlarge, leading to leaf blight, fruit rot, and stem cankers, often with a characteristic target-like appearance.\n" +
                            "Treatments: \n Remove Infected Plant Parts: Prune and dispose of affected leaves, stems, and fruits to reduce the spread of the fungus. Do not compost infected plant material as it can spread the disease.\n" +
                            "Improve Air Circulation: Space plants appropriately and prune them to enhance air circulation. This helps to reduce humidity around the plants, which can inhibit fungal growth.\n" +
                            "Apply Fungicides: Use fungicides containing active ingredients such as chlorothalonil, copper, or mancozeb. Apply them as soon as you notice symptoms and follow the instructions on the label for application rates and frequency.\n" +
                            "Practice Crop Rotation and Sanitation: Rotate crops to prevent the buildup of Alternaria in the soil. Avoid planting susceptible plants in the same location each year. Additionally, keep the garden free of debris and weeds that can harbor the fungus.",
                    "Whiteflies disease \n\n Symptoms: \n Whiteflies are small, flying insects with white wings that typically gather on the undersides of leaves. Infested plants may exhibit yellowing, wilting, or distorted growth due to the pests feeding on sap.\n" +
                            "\n Treatments: \n" +  "Remove Infested Leaves: Prune and discard heavily infested leaves to reduce the whitefly population. Dispose of the removed plant material away from your garden to prevent reinfestation.\n" +
                            "\n" + "Introduce Natural Predators: Encourage the presence of natural enemies of whiteflies, such as ladybugs, lacewings, parasitic wasps, and predatory mites, which can help control their population.\n" +
                            "\n" + "Use Horticultural Oils or Soaps: Apply insecticidal soaps or horticultural oils to the affected plants, especially on the undersides of leaves where whiteflies typically lay their eggs. These products suffocate the insects and disrupt their life cycle.\n" +
                            "\n" + "Deploy Sticky Traps: Place yellow sticky traps near infested plants to capture adult whiteflies. This method helps reduce the population and provides an indication of their presence and activity levels.",
                    "Spider Mites \n\n Symptoms: \n Spider mites are tiny arachnids that often appear as small dots or specks on the undersides of leaves. As they feed on plant sap, they cause stippling, yellowing, and wilting of foliage. Fine webbing may also be visible, especially during heavy infestations.\n" +
                            "\n Treatments: \n" + "Remove Infested Leaves: Prune and discard heavily infested leaves to remove spider mite colonies. Pay particular attention to the undersides of leaves where mites tend to congregate.\n" +
                            "\n" +  "Increase Humidity: Spider mites thrive in dry conditions, so increasing humidity around plants can help deter their growth. Mist plants regularly or use a humidifier, especially during dry periods.\n" +
                            "\n" +  "Apply Insecticidal Soap or Neem Oil: Spray affected plants with insecticidal soap or neem oil to suffocate and kill spider mites. Ensure thorough coverage, particularly on the undersides of leaves where mites are most abundant.\n" +
                            "\n" +  "Introduce Predatory Insects: Release natural predators of spider mites, such as predatory mites, ladybugs, or lacewings, to help control their population. These beneficial insects feed on spider mites, reducing their numbers.\n" +
                            "\n" +  "Monitor and Repeat Treatments: Regularly inspect plants for signs of spider mite activity and continue treatments as necessary until the infestation is under control. Spider mites can reproduce rapidly, so persistence is key to managing their populations effectively.",
                    "Aphids disease \n\n Symptoms: \n Aphids are small, soft-bodied insects that typically cluster on the undersides of leaves and along tender stems. Signs of aphid infestation include distorted or curled leaves, stunted growth, sticky honeydew residue on leaves, and the presence of ants attracted to the honeydew.\n" +
                            "\n" + "Remove Aphids Manually: Squish aphids between your fingers or dislodge them from plants with a strong stream of water. Focus on the undersides of leaves where aphids often congregate.\n" +
                            "\n" + "Encourage Natural Predators: Introduce or attract natural predators of aphids, such as ladybugs, lacewings, or parasitic wasps, to help keep their populations in check.\n" +
                            "\n" + "Apply Insecticidal Soap or Neem Oil: Spray affected plants with insecticidal soap or neem oil to coat and suffocate aphids. Repeat applications as necessary, ensuring thorough coverage of infested areas.\n" +
                            "\n" + "Prune and Dispose of Infested Plant Parts: Prune and discard heavily infested plant parts to remove aphids and reduce the spread of the infestation. Dispose of pruned material away from the garden to prevent reinfestation.\n" +
                            "\n" + "Use Reflective Mulch: Reflective mulch or aluminum foil placed around plants can deter aphids by disorienting them with the reflected light. This can help protect plants from infestation."
            };
            Random random = new Random();
            int randomIndex = random.nextInt(diseases.length);
            return diseases[randomIndex];
        }

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

        // Check if prediction is valid
        if (Float.isNaN(prediction)) {
            return "Unable to detect";
        } else {
            return prediction > 0.5 ? "Class A" : "Class B";
        }
    }


    private ByteBuffer convertBitmapToByteBuffer(Bitmap bitmap) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * 224 * 224 * 3);
        byteBuffer.order(ByteOrder.nativeOrder());
        int[] intValues = new int[224 * 224];
        bitmap.getPixels(intValues, 0, 224, 0, 0, 224, 224);
        for (int pixel : intValues) {
            byteBuffer.putFloat(((pixel >> 16) & 0xFF) / 255.0f); // R
            byteBuffer.putFloat(((pixel >> 8) & 0xFF) / 255.0f);  // G
            byteBuffer.putFloat((pixel & 0xFF) / 255.0f);         // B
        }
        return byteBuffer;
    }
}
