package com.example.snd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CheckPairedConnection extends AppCompatActivity {
    Button btnOKAddDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_paired_connection);
        /**Set ActionSupportBar in Center**/
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.support_action_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);/**For Getting back button**/

        btnOKAddDevice = findViewById(R.id.btnOKAddDevice);

        btnOKAddDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Home.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(),"Thanks for connecting.!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
