package com.example.snd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class VerifyOTP extends AppCompatActivity {
    Button btnVerifyotp;
    EditText etFst,etSec,etThir,etForth;
    TextView tvMobNo;
    String OTP;
    String MobileNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        /**Set ActionSupportBar in Center**/
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.otp_actionbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);/**For Getting back button**/
        btnVerifyotp = findViewById(R.id.btnVerifyotp);
        tvMobNo = findViewById(R.id.tvMobNo);
        etFst = findViewById(R.id.etFst);
        etSec = findViewById(R.id.etSec);
        etThir = findViewById(R.id.etThir);
        etForth = findViewById(R.id.etForth);
                /**Receving intent from Register**/
        MobileNo = getIntent().getExtras().getString("MobilNo");
        tvMobNo.setText("mobile "+MobileNo);


            btnVerifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateOtp())
                    return;
                OTP = etFst.getText().toString()+etSec.getText().toString()+etThir.getText().toString()+etForth.getText().toString();

                Intent i = new Intent(getApplicationContext(),CreatePassword.class);
                startActivity(i);
                /**Code To call API**/
                String myURL = "https://apex.oracle.com/pls/apex/neha_semwal/verifyotp/insert/";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, myURL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    Log.e("TAG", "jsonObject " + jsonObject);
                                    /**Intent For Next Page**/
                                    Intent i = new Intent(getApplicationContext(),CreatePassword.class);
                                    startActivity(i);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Log.e("JSON Parser", "Error parsing data [" + e.getMessage() + "] " + response);
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(VerifyOTP.this, error.getMessage(), Toast.LENGTH_LONG).show();
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

        /**Code To Move Cursor To next EditText Automatic**/
        etFst.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Integer textlength1 = etFst.getText().length();
                if (textlength1 >= 1)
                    etSec.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etSec.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Integer textlength1 = etFst.getText().length();
                if (textlength1 >= 1)
                    etThir.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etThir.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Integer textlength1 = etFst.getText().length();
                if (textlength1 >= 1)
                    etForth.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private  boolean validateOtp(){
        String OtpInput1 = etFst.getText().toString().trim();
        String OtpInput2 = etSec.getText().toString().trim();
        String OtpInput3 = etThir.getText().toString().trim();
        String OtpInput4 = etForth.getText().toString().trim();
        if (OtpInput1.isEmpty()){
            etFst.setError("_");
            return false;
        } else if (OtpInput2.isEmpty()){
            etSec.setError("_");
            return false;
        } else if (OtpInput3.isEmpty()){
            etThir.setError("_");
            return false;
        }else if (OtpInput4.isEmpty()){
            etForth.setError("_");
            return false;
        }
         else {
            return true;
        }
    }

    private String apiParameter() {
        String urlString = null;
        try {
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonObject1 = new JSONObject();
            jsonObject.put("API", "AMAT_VERIFY_OTP");
            jsonObject.put("CURD_OPERATION", "G");
            jsonObject.put("MOBILE_NUMBER", MobileNo);
            jsonObject.put("OTP", OTP);
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
