package com.example.oleksandr.lockscreenproject;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;

import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.util.Base64;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

/**
 * Created by Oleksandr on 20/10/2016.
 */
public class PasswordReceiver extends DeviceAdminReceiver implements PhotoTakenCallback {

    private static int attempts = 0;
    private static String email;

    @Override
    public void onPasswordSucceeded(Context context, Intent intent) {
        super.onPasswordSucceeded(context, intent);
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        attempts = 0;
    }

    @Override
    public void onPasswordFailed(Context context, Intent intent) {
        super.onPasswordFailed(context, intent);
        attempts ++;
        Toast.makeText(context, "Fail: " + attempts, Toast.LENGTH_SHORT).show();
        if(attempts > 0){
            email = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE).getString("email", null);
            //TakePhoto takePhoto = new TakePhoto(this, CameraMode.FRONT);
            SendMail mail = new SendMail();
            mail.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "infinitysasha.altervista.org", email);
        }
    }

    @Override
    public void onPhotoTaken(byte[] photoBytes) {
        SendMail mail = new SendMail();
        String photo = Base64.encodeToString(photoBytes, 0);
        mail.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "infinitysasha.altervista.org", email, photo);
    }

    @Override
    public void onPhotoTaken(byte[] photo1Bytes, byte[] photo2Bytes) {
        SendMail mail = new SendMail();
        String photo1 = Base64.encodeToString(photo1Bytes, 0);
        String photo2 = Base64.encodeToString(photo2Bytes, 0);
        mail.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "infinitysasha.altervista.org", email, photo1, photo2);
    }
}
