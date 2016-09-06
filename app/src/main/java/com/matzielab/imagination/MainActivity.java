package com.matzielab.imagination;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    RelativeLayout layout;
    TextView textOutput;

    Sensor accelerometer;
    SensorManager sensorManager;

    Imagination imagination;

    private void initSensor() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, sensorManager.SENSOR_DELAY_NORMAL);
    }

    private void killSensor() {
        sensorManager.unregisterListener(this);
        sensorManager = null;
        accelerometer = null;
    }

    private void setTextOutput(String output) {
        textOutput.setText(output);
    }

    private void setLayoutBG(int R, int G, int B) {
        int BGColor = Color.rgb(R,G,B);
        layout.setBackgroundColor(BGColor);
    }

    private void sensorChange(SensorEvent event) {
        float X = event.values[0];
        float Y = event.values[1];
        float Z = event.values[2];

        String XYZ = "X: "+X+"\nY: "+Y+"\nZ: "+Z;
        setTextOutput(XYZ);

        HashMap<String, Float> RGB = imagination.rgbFromAccelerometer(X, Y, Z);

        int R = Math.round(RGB.get("R"));
        int G = Math.round(RGB.get("G"));
        int B = Math.round(RGB.get("B"));

        setLayoutBG(R, G, B);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (RelativeLayout) findViewById(R.id.mainLayout);
        textOutput = (TextView) findViewById(R.id.mainTextView);
        imagination = new Imagination();
        initSensor();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        initSensor();
    }

    @Override
    protected void onPause() {
        super.onPause();
        killSensor();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        sensorChange(event);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not implemented because this shizzle is not needed in our bizzle
    }
}
