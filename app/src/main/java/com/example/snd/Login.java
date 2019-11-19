package com.example.snd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button btnLogin;
    TextView tvSignUp;
    EditText etMobile,etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        btnLogin = findViewById(R.id.btnLogin);
        tvSignUp = findViewById(R.id.tvSignUp);
        etMobile = findViewById(R.id.etMobile);
        etPassword = findViewById(R.id.etPassward);

        /**Go To Home Page**/
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateMobile())
                    return;
                Intent i = new Intent(getApplicationContext(),Home.class);
                startActivity(i);

            }
        });

        /**Got To Register Page**/
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Register.class);
                startActivity(i);
            }
        });
        /**TO Get Location Access**/
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},0);
        }

    }

    /**Function To Validate Mobile**/
    private  boolean validateMobile(){
        String mobileInput = etMobile.getText().toString().trim();
        String inputPassword = etPassword.getText().toString().trim();
        if (mobileInput.isEmpty()){
            etMobile.setError("can't be empty");
            return false;
        } else if (mobileInput.length()<10 || mobileInput.startsWith(String.valueOf(0)) ) {
            etMobile.setError("Enter Valid 10 Digit mobile no.");
            return false;
        } else if(inputPassword.isEmpty()){
            etPassword.setError("Enter Password");
            return false;
        }else {
            etMobile.setError(null);
            return true;
        }
    }
}
