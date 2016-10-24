package com.example.oleksandr.lockscreenproject;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import org.opencv.android.OpenCVLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by User on 24/10/2016.
 */

public class OpenCVProva extends AppCompatActivity implements PhotoTakenCallback{

    public static final String TAG = "OpenCVProva";
    public Drawable photo;
    private ImageView imageView;


    static{
        if(!OpenCVLoader.initDebug()){
            Log.d(TAG,"OpenCV Not started");
        }else{
            Log.d(TAG,"OpenCV Started");
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opencv_layout);
        imageView = (ImageView)findViewById(R.id.imageViewOpenCV);
        photo = loadImageFromAssets("lena.png");
        imageView.setImageDrawable(photo);
    }

    private Drawable loadImageFromAssets(String filename){
        try {
            InputStream ims = getAssets().open(filename);
            // load image as Drawable perchè di si
            Drawable d = Drawable.createFromStream(ims, null);
            return d;
        }
        catch(IOException ex) {
            System.out.println("OpenCV - ERRROR READING IMAGE");
            return null;
        }
    }

    @Override
    public void onPhotoTaken(byte[] photoBytes) {

    }

    @Override
    public void onPhotoTaken(byte[] photo1Bytes, byte[] photo2Bytes) {

    }
}
