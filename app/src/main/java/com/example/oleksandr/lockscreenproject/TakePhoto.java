package com.example.oleksandr.lockscreenproject;

/**
 * Created by Oleksandr on 21/10/2016.
 */
public class TakePhoto extends Thread {

    private PhotoTakenCallback callback;
    private CameraMode mode;

    public  TakePhoto(PhotoTakenCallback callback, CameraMode mode){
        this.callback = callback;
        this.mode = mode;
        this.start();
    }

    @Override
    public void run() {

        /*
        Se c'è qulcosa che non va, poi cambiare tutto quello di qui hai bisogno;
        callback.onPhotoTaken(...) - chiama quando hai scatato la foto;
        Per farlo funzionare basta che scomenti TakePhoto takePhoto = new TakePhoto(this, CameraMode.FRONT)
                in PasswordReceiver(line 37) e commenti il resto;
        Non ho fatto la roba per cambiare CameraMode, quindi cambialo a mano;
        Per leggere la risposta dal server leggi la comandLine "Buffer: ..." (SendMail line 49);
            Server risposte:
                Sent: la email inviata;
                Photo1/2 received: photo1/2 ricevuta;
                No photo1/2: photo1/2 non ricevuta
         */

        switch (mode){
            case ALL:

                break;
            case BACK:

                break;
            case FRONT:

                break;
            case NONE:
                return;
            default:
                return;
        }
    }
}