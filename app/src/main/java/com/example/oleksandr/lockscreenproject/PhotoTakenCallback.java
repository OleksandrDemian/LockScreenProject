package com.example.oleksandr.lockscreenproject;

/**
 * Created by Oleksandr on 24/10/2016.
 */
public interface PhotoTakenCallback {

    void onPhotoTaken(byte[] photoBytes);
    void onPhotoTaken(byte[] photo1Bytes, byte[] photo2Bytes);

}
