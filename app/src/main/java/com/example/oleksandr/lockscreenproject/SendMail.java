package com.example.oleksandr.lockscreenproject;

import android.net.Uri;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Calendar;


/**
 * Created by Oleksandr on 20/10/2016.
 */
public class SendMail extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... params) {
        URL url;
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http").authority(params[0]).appendPath("AntiThefMail.php");

        if(params[1] == null)
            return null;

        builder.appendQueryParameter("mail", params[1]);
        if(params.length > 2)
            builder.appendQueryParameter("photo1", params[2]);
        if(params.length > 3)
            builder.appendQueryParameter("photo2", params[3]);
        builder.appendQueryParameter("message","Someone tried to unlock your phone!\nDate: " + Calendar.getInstance().getTime());

        try {
            url = new URL(builder.toString());
            System.out.println("URL: " + url.toString());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream == null) {
                return null;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String result = "";
            String temp = "";
            while((temp = reader.readLine()) != null){
                result += temp;
            }
            System.out.println("Buffer: " + result);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done");
        return null;
    }
}
