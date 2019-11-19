package com.example.snd;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.lang.reflect.Method;


public class MainActivity extends AppCompatActivity {
    TextView tvLoginExis;
    Button btnRegister;


    @Override   /**Exit the app on backpressed***/
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        tvLoginExis = findViewById(R.id.tvLoginExis);
        btnRegister = findViewById(R.id.btnRegister);
                /**Turn On Wifi Automatically**/
        WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifi.setWifiEnabled(true);

        /**Go to Login Activity**/
        tvLoginExis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
     /*           if (!configApState(getApplicationContext()))
                    return;
     */           Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });

        /**GO to Registration Page**/
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Register.class);
                startActivity(i);
            }
        });

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},0);
        }
    }

}
