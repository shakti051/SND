package com.example.snd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreatePassword extends AppCompatActivity {
    Button btnSubmitPassword;
    EditText etNewPass,etCnfPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);
        getSupportActionBar().setTitle("Registration");
        btnSubmitPassword = findViewById(R.id.btnSubmitPass);
        etNewPass = findViewById(R.id.etNewPass);
        etCnfPass = findViewById(R.id.etCnfPass);

        btnSubmitPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validatePassword())
                    return;
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
                Toast.makeText(CreatePassword.this,"Password Saved Successfully.",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private  boolean validatePassword(){
        final String newPass = etNewPass.getText().toString().trim();
        final String cnfPass = etCnfPass.getText().toString().trim();

        if (newPass.isEmpty()){
            etNewPass.setError("can't be empty");
            return false;
        } else if (!newPass.equals(cnfPass)) {
            etCnfPass.setError("Password Missmatched.");
            return false;
        } else {
            return true;
        }
    }
}
