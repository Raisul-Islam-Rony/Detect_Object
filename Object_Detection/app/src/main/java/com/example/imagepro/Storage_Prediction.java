package com.example.imagepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;

import java.io.IOException;
import java.util.Locale;

public class Storage_Prediction extends AppCompatActivity {

    private Button button;
    private ImageView imageView;
    private objectDetectorClass objectDetectorClass;
    int SELECT_PICTURE=200;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_storage_prediction);


       // button=findViewById(R.id.select_image);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();
        imageView=findViewById(R.id.image_V);

        try{
            // input size is 300 for this model
            objectDetectorClass=new objectDetectorClass(getAssets(),"ssd_mobilenet.tflite","labelmap.txt",300);
            Log.d("MainActivity","Model is successfully loaded");
        }
        catch (IOException e){
            Log.d("MainActivity","Getting some error");
            e.printStackTrace();
        }

        Intent i=new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(i,"Select Picture"),SELECT_PICTURE);

    }

    private void image_chooser()
    {
        Intent i=new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(i,"Select Picture"),SELECT_PICTURE);

    }

    public void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK)
        {
            if(requestCode==SELECT_PICTURE)
            {
                Uri selectedImageUri=data.getData();

                if(selectedImageUri!=null)
                {
                    Bitmap bitmap=null;

                    try {
                        bitmap= MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImageUri);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    Mat selected_image=new Mat(bitmap.getHeight(),bitmap.getWidth(),CvType.CV_8UC4);
                    Utils.bitmapToMat(bitmap,selected_image);
                    selected_image=objectDetectorClass.recognizePhoto(selected_image);

                    String s =objectDetectorClass.getStorage_photo();
                    Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();

                       textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i==TextToSpeech.SUCCESS){
                    int lang = textToSpeech.setLanguage(Locale.ENGLISH);

                    textToSpeech.setSpeechRate(1.0f);
                    if(s.equals(""))
                    {
                        int speech=textToSpeech.speak("Cannot Detect Anything", TextToSpeech.QUEUE_FLUSH,null);
                    }
                    else
                    {
                        int speech = textToSpeech.speak("Photo Contains "+s,TextToSpeech.QUEUE_FLUSH,null);
                    }
                }
            }
        });

                    Bitmap bitmap1=null;
                    bitmap1=Bitmap.createBitmap(selected_image.cols(),selected_image.rows(),Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(selected_image,bitmap1);

                    imageView.setImageBitmap(bitmap1);

                }
            }
        }

    }


}