package com.example.imagepro;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import java.util.ArrayList;
import java.util.List;

public class Do_Work extends AppCompatActivity   implements SensorEventListener {
    TextView x,y,z;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState)  {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor);


        x=findViewById(R.id.x_axis);
        y=findViewById(R.id.y_axis);
        z=findViewById(R.id.z_axis);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

       /// mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);

}



        protected void onResume() {

        super.onResume();

        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        }

        protected void onPause() {

        super.onPause();

        mSensorManager.unregisterListener(this);

        }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float a= event.values[0];

        float b = event.values[1];

        float c = event.values[2];

        x.setText("X text: " +Float.toString(a));
        y.setText("Y text : "+Float.toString(b));
        z.setText("Z text :" +Float.toString(c));

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}