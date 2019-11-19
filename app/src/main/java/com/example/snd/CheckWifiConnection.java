package com.example.snd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CheckWifiConnection extends AppCompatActivity {
    Button btnOKAddDevice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_wifi_connection);
        /**Set ActionSupportBar in Center**/
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.support_wifi_actionbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);/**For Getting back button**/
        btnOKAddDevice = findViewById(R.id.btnOKAddDevice);
        btnOKAddDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Home.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(),"Welcome to Home Page. Thanks!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
