package com.matzielab.imagination;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity {

    RelativeLayout backgroundLayout;
    ViewPager pager;

    SensorEventListener sensorListener;

    ImaginationAccelerometer imagination;

    private void changeAccelerometerBackground(SensorEvent event) {
        float X = event.values[0], Y = event.values[1], Z = event.values[2];
        int color = imagination.rgbFromAccelerometer(X, Y, Z);
        backgroundLayout.setBackgroundColor(color);
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

    private void initPager() {
        PagerAdapter adapter = new PagerAdapter(this, getSupportFragmentManager());
        pager.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        backgroundLayout = (RelativeLayout) findViewById(R.id.imagination_background);
        pager = (ViewPager) findViewById(R.id.main_viewpager);

        initPager();
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
