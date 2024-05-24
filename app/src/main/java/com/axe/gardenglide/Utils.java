package com.axe.gardenglide;

import android.content.Context;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static Map<String, float[]> loadScalerParams(Context context, String fileName) {
        Map<String, float[]> scalerParams = new HashMap<>();
        try {
            InputStream is = context.getAssets().open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            JSONObject json = new JSONObject(sb.toString());
            scalerParams.put("min", toFloatArray(json.getJSONArray("min")));
            scalerParams.put("max", toFloatArray(json.getJSONArray("max")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scalerParams;
    }

    private static float[] toFloatArray(JSONArray jsonArray) throws Exception {
        float[] array = new float[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            array[i] = (float) jsonArray.getDouble(i);
        }
        return array;
    }

    public static List<String> loadLabelClasses(Context context, String fileName) {
        List<String> labelClasses = new ArrayList<>();
        try {
            InputStream is = context.getAssets().open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            JSONArray json = new JSONArray(sb.toString());
            for (int i = 0; i < json.length(); i++) {
                labelClasses.add(json.getString(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return labelClasses;
    }
}