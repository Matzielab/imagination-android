package com.matzielab.imagination;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener{

    RelativeLayout layout;
    ImaginationAccelerometer imagination;

    private void changeBackgroundColor(int color) {
        layout.setBackgroundColor(color);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        layout = (RelativeLayout) findViewById(R.id.activity_accelerometer);
        imagination = new ImaginationAccelerometer(this);
        imagination.setListener(this).init();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float X = event.values[0], Y = event.values[1], Z = event.values[2];
        int color = imagination.rgbFromAccelerometer(X, Y, Z);
        changeBackgroundColor(color);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Naathiiing, we don't need this shizzle
    }
}
