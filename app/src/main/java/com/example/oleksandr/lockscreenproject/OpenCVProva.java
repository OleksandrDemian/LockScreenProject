package com.example.oleksandr.lockscreenproject;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

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
        int height = photo.getIntrinsicHeight();
        int width = photo.getIntrinsicWidth();
        Mat frame = new Mat();
        try {
            frame = Utils.loadResource(getApplicationContext(), R.id.imageViewOpenCV, Imgcodecs.CV_LOAD_IMAGE_COLOR);
            System.out.println("Loaded mat succesfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Abbiamo il frame, ora creiamo il face detector
        CascadeClassifier faceDetector =  new CascadeClassifier();
        faceDetector.load(getCacheDir()+"/lbpcascade_frontalface.xml");
        //Ora cominciamo a cercare
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(frame, faceDetections);

        System.out.println(String.format("Trovate %s facce", faceDetections.toArray().length));

        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
        }
        //E ora stampiamola
        Bitmap bmp = null;
        try{
            //Mat tmp = new Mat (height, width, CvType.CV_8U, new Scalar(4));
            //Imgproc.cvtColor(frame, tmp, Imgproc.COLOR_GRAY2RGBA, 4);
            bmp = Bitmap.createBitmap(frame.cols(), frame.rows(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(frame, bmp);
        }catch(Exception e){System.out.println("OpenCV - Unable to translate to bitmap");}

        imageView.setImageBitmap(bmp);
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
