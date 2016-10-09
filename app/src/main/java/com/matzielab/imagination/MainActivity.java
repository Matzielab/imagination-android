package com.matzielab.imagination;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    RelativeLayout accelerometerLayout;
    TextView accelerometerTitle;
    SensorEventListener sensorListener;

    ImaginationAccelerometer imagination;

    public void startAccelerometerActivity(View view) {
        Intent intent = new Intent(this, AccelerometerActivity.class);

        Pair<View, String> layoutPair = Pair.create((View) accelerometerLayout, accelerometerLayout.getTransitionName());
        Pair<View, String> titlePair = Pair.create((View) accelerometerTitle, accelerometerTitle.getTransitionName());

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, layoutPair, titlePair);
        startActivity(intent, options.toBundle());
    }

    private void changeAccelerometerBackground(SensorEvent event) {
        float X = event.values[0], Y = event.values[1], Z = event.values[2];
        int color = imagination.rgbFromAccelerometer(X, Y, Z);
        accelerometerLayout.setBackgroundColor(color);
    }

    private void initSensorListener() {
        sensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                Sensor sensor = event.sensor;
                if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                    changeAccelerometerBackground(event);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accelerometerLayout = (RelativeLayout) findViewById(R.id.accelerometer_item);
        accelerometerTitle = (TextView) findViewById(R.id.accelerometer_title);
        initSensorListener();

        imagination = new ImaginationAccelerometer(this)
                .setListener(sensorListener)
                .init();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        initSensorListener();
        imagination.setListener(sensorListener).init();
    }

    @Override
    protected void onPause() {
        super.onPause();
        imagination.kill();
    }
}
