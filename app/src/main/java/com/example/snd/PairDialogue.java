package com.example.snd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PairDialogue extends AppCompatActivity {
    TextView tvClickme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair_dialogue);
        tvClickme = findViewById(R.id.tvClickme);
        tvClickme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),CheckPairedConnection.class);
                startActivity(i);
            }
        });
    }
}
