package com.matzielab.imagination;

import java.util.HashMap;

/**
 * Created by Matzie on 16-09-06.
 */
public class Imagination {

    // Values for scale
    private float ACCELEROMETER_MIN = -10;
    private float ACCELEROMETER_MAX = 10;

    private float makeBetween1And0(float number) {
        if(number > 1) {
            number = 1;
        } else if(number < 0) {
            number = 0;
        }

        return number;
    }

    // Takes accelerometer xyz values and returns color
    public HashMap<String, Float> rgbFromAccelerometer(float X, float Y, float Z) {

        HashMap<String, Float> RGB = new HashMap<>();

        float scaledX = X+(0-(ACCELEROMETER_MIN));
        float scaledY = Y+(0-(ACCELEROMETER_MIN));
        float scaledZ = Z+(0-(ACCELEROMETER_MIN));

        float Xpercentage = makeBetween1And0(scaledX/(ACCELEROMETER_MAX*2));
        float Ypercentage = makeBetween1And0(scaledY/(ACCELEROMETER_MAX*2));
        float Zpercentage = makeBetween1And0(scaledZ/(ACCELEROMETER_MAX*2));

        float R = 255*Xpercentage;
        float G = 255*Ypercentage;
        float B = 255*Zpercentage;

        RGB.put("R", R);
        RGB.put("G", G);
        RGB.put("B", B);

        return RGB;
    }

}
