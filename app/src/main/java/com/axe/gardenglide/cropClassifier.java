package com.axe.gardenglide;

// CropClassifier.java
import android.content.Context;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.common.FileUtil;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;
import org.tensorflow.lite.support.tensorbuffer.TensorBufferFloat;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.util.List;
import java.util.Map;

public class cropClassifier {
    private Interpreter interpreter;
    private float[] dataMin;
    private float[] dataMax;
    private List<String> labelClasses;

    public cropClassifier(Context context) throws IOException {
        MappedByteBuffer modelFile = FileUtil.loadMappedFile(context, "crop_classification_model.tflite");
        interpreter = new Interpreter(modelFile);

        Map<String, float[]> scalerParams = Utils.loadScalerParams(context, "scaler_params.json");
        dataMin = scalerParams.get("min");
        dataMax = scalerParams.get("max");

        labelClasses = Utils.loadLabelClasses(context, "label_classes.json");
    }

//    public String classify(float[] input) {
//        // Scale input values
//        float[] scaledInput = scaleInput(input);
//
//        TensorBuffer inputBuffer = TensorBufferFloat.createFixedSize(new int[]{1, input.length}, DataType.FLOAT64);
//        inputBuffer.loadArray(scaledInput);
//
//        TensorBuffer outputBuffer = TensorBufferFloat.createFixedSize(new int[]{1, labelClasses.size()}, DataType.object);
//        interpreter.run(inputBuffer.getBuffer(), outputBuffer.getBuffer().rewind());
//
//        return getLabel(outputBuffer.getFloatArray());
//    }

    private float[] scaleInput(float[] input) {
        float[] scaledInput = new float[input.length];
        for (int i = 0; i < input.length; i++) {
            scaledInput[i] = (input[i] - dataMin[i]) / (dataMax[i] - dataMin[i]);
        }
        return scaledInput;
    }

    private String getLabel(float[] outputArray) {
        int maxIndex = -1;
        float maxProb = -1;
        for (int i = 0; i < outputArray.length; i++) {
            if (outputArray[i] > maxProb) {
                maxProb = outputArray[i];
                maxIndex = i;
            }
        }
        return labelClasses.get(maxIndex);
    }
}