package com.example.imagepro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.OpenCVLoader;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.opencv.android.OpenCVLoader;

import java.io.IOException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity  {


   static {
        if(OpenCVLoader.initDebug()){
            Log.d("MainActivity: ","Opencv is loaded");
        }
        else {
            Log.d("MainActivity: ","Opencv failed to load");
        }
    }
    private Button real,storage;
    Button b;
    private Button camera_button;
   private Button storage_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



    //  b=findViewById(R.id.button_1);







       // b.setOnClickListener(new View.OnClickListener() {
         //   @Override
           // public void onClick(View v) {
                        //        final  Dialog dialog=new Dialog(v.getRootView().getContext());
       // View view = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.dialog_box,null);


       // real=(Button)view.findViewById(R.id.Real_Time_Button);
       // storage=(Button)view.findViewById(R.id.Storage_Button);
//
     //   dialog.setContentView(view);
       // dialog.setCancelable(true);

       // real.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View v2) {
               //Intent intent=new Intent(MainActivity.this,CameraActivity.class);
               // startActivity(intent);
           // }
       // });

       // storage.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v3) {
               // Intent intent=new Intent(MainActivity.this,Storage_Prediction.class);
               // startActivity(intent);

           // }
        //});

       /////// AlertDialog alertDialog=new AlertDialog();



        //dialog.show();


            //}
        //});

          camera_button=findViewById(R.id.camera);
        storage_button=findViewById(R.id.storage);

        camera_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CameraActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        storage_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Storage_Prediction.class);
                startActivity(intent);
            }
        });








        // select device and run
        // we successfully loaded model
        // before next tutorial
        // as we are going to predict in Camera Activity
        // Next tutorial will be about predicting using Interpreter

       // camera_button=findViewById(R.id.button_1);
       // camera_button.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View v) {
            //    Intent intent=new Intent(MainActivity.this, MainActivity2.class);
           //     startActivity(intent);
           // }
      //  });




    }

    @Override
    public void onBackPressed() {
        finish();
    }
}