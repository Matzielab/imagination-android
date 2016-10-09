package com.matzielab.imagination;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import java.util.HashMap;

/**
 * Created by Matzie on 16-09-07.
 */
public class ImaginationAccelerometer {

    private Context CONTEXT;

    private SensorEventListener LISTENER;

    // Values for scale
    private final float ACCELEROMETER_MIN = -10;
    private final float ACCELEROMETER_MAX = 10;

    private SensorManager sensorManager;
    private Sensor accelerometer;

    public ImaginationAccelerometer(Context context) {
        CONTEXT = context;
    }

    // Takes accelerometer xyz values and returns color
    public int rgbFromAccelerometer(float X, float Y, float Z) {

        float scaledX = X+(0-(ACCELEROMETER_MIN));
        float scaledY = Y+(0-(ACCELEROMETER_MIN));
        float scaledZ = Z+(0-(ACCELEROMETER_MIN));

        float Xpercentage = ImaginationHelper.makeBetween1And0(scaledX/(ACCELEROMETER_MAX*2));
        float Ypercentage = ImaginationHelper.makeBetween1And0(scaledY/(ACCELEROMETER_MAX*2));
        float Zpercentage = ImaginationHelper.makeBetween1And0(scaledZ/(ACCELEROMETER_MAX*2));

        int R = Math.round(255*Xpercentage);
        int G = Math.round(255*Ypercentage);
        int B = Math.round(255*Zpercentage);

        int RGB = Color.rgb(R, G, B);

        return RGB;
    }

    public ImaginationAccelerometer setListener(SensorEventListener listener) {
        LISTENER = listener;
        return this;
    }

    public ImaginationAccelerometer init() {
        sensorManager = (SensorManager) CONTEXT.getSystemService(CONTEXT.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(LISTENER, accelerometer, sensorManager.SENSOR_DELAY_NORMAL);
        return this;
    }

    public ImaginationAccelerometer kill() {
        sensorManager.unregisterListener(LISTENER);
        sensorManager = null;
        accelerometer = null;
        return this;
    }

}
