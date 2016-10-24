package com.example.oleksandr.lockscreenproject;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DevicePolicyManager devicePolicyManager;
    ComponentName adminReceiver;
    EditText emailTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        devicePolicyManager = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
        adminReceiver = new ComponentName(this, PasswordReceiver.class);

        Button btnStart = (Button)findViewById(R.id.startButton);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startReceiver();
            }
        });

        Button btnStop = (Button)findViewById(R.id.stopButton);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopReceiver();
            }
        });

        Button btnEmailSubmit = (Button)findViewById(R.id.emailSubmit);
        btnEmailSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitEmail();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        emailTxt = (EditText)findViewById(R.id.emailText);
        String email = getSharedPreferences("myPrefs", MODE_PRIVATE).getString("email", "");
        emailTxt.setText(email);
    }

    private void submitEmail(){
        String email = emailTxt.getText().toString();
        SharedPreferences.Editor editor = getSharedPreferences("myPrefs", Context.MODE_PRIVATE).edit();
        editor.putString("email", email);
        editor.apply();
        Toast.makeText(this, "New email is: " + email, Toast.LENGTH_LONG).show();
    }

    private void startReceiver(){
        if (!devicePolicyManager.isAdminActive(adminReceiver)) {
            Intent intent = null;
            intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, adminReceiver);
            Toast.makeText(this, "Start", Toast.LENGTH_LONG).show();
            startActivity(intent);
        } else {
            Toast.makeText(this, "Is already active", Toast.LENGTH_LONG).show();
        }
    }

    private void stopReceiver(){
        if(devicePolicyManager.isAdminActive(adminReceiver)) {
            Toast.makeText(this, "Stop", Toast.LENGTH_LONG).show();
            devicePolicyManager.removeActiveAdmin(adminReceiver);
        } else {
            Toast.makeText(this, "Is not active", Toast.LENGTH_LONG).show();
        }
    }
}
