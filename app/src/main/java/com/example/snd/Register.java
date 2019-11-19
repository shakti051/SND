package com.example.snd;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    Button btnGetCode;
    EditText etEnterMobile;
    ProgressBar progressRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Registration");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);/**For Getting back button**/
        btnGetCode = findViewById(R.id.btnGetCode);
        etEnterMobile = findViewById(R.id.etEnterMobile);
        progressRegister = findViewById(R.id.progressRegister);

        /**Get Verification Code.**/
        btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateMobile())
                    return;
                progressRegister.setVisibility(View.VISIBLE);
                /**Code To call API**/
                String myURL = "https://apex.oracle.com/pls/apex/neha_semwal/amatnumber/registered/";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, myURL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    Toast.makeText(Register.this, "Welcome", Toast.LENGTH_SHORT).show();
                                    Log.e("TAG", "jsonObject " + jsonObject);

                                    String MobNo = etEnterMobile.getText().toString();
                                    /**Intent For Next Page**/
                                    Intent i = new Intent(getApplicationContext(),VerifyOTP.class);
                                    i.putExtra("MobilNo",MobNo);/**Passing String To Next Page**/
                                    startActivity(i);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Log.e("JSON Parser", "Error parsing data [" + e.getMessage() + "] " + response);
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Register.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("a", apiParameter());

                        Log.e("TAG", "params" + params.put("a", apiParameter()));
                        return params;
                    }
                };
                MyApplication.getInstance().addToRequestQueue(stringRequest);
                /**End of code sending data to web server.**/
            }
        });
    }

    private  boolean validateMobile(){
        String mobileInput = etEnterMobile.getText().toString().trim();
        if (mobileInput.isEmpty()){
            etEnterMobile.setError("can't be empty");
            return false;
        } else if (mobileInput.length()<10 || mobileInput.startsWith(String.valueOf(0)) ) {
            etEnterMobile.setError("Enter Valid 10 Digit mobile no.");
            return false;
        } else {
            etEnterMobile.setError(null);
            return true;
        }
    }

    private String apiParameter() {
        String urlString = null;
        try {
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonObject1 = new JSONObject();
            jsonObject.put("API", "AMAT_REGISTRATION_API");
            jsonObject.put("CURD_OPERATION", "I");
            jsonObject.put("MOBILE_NUMBER", etEnterMobile.getText().toString());
            jsonObject.put("PASSWORD", "TEST");
            jsonObject1.put("ITEMS", jsonObject);
            urlString = jsonObject1.toString();
            Log.e("TAG", "urlString" + urlString);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("EXCEPTION>>>", "" + String.valueOf(e));
        }
        return urlString;
    }
}
