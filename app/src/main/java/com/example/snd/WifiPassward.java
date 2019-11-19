package com.example.snd;

import androidx.appcompat.app.ActionBar;
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

public class WifiPassward extends AppCompatActivity {
    Button btnConnect;
    EditText etWifiName,etWifiPassword;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_passward);
        btnConnect = findViewById(R.id.btnConnect);
        etWifiName = findViewById(R.id.etWifiName);
        etWifiPassword = findViewById(R.id.etWifiPassword);
        /**Set ActionSupportBar in Center**/
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.support_wifi_actionbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);/**For Getting back button**/

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**Open Custom Dialog Box**/
            /*final AlertDialog.Builder alert = new AlertDialog.Builder(WifiPassward.this);
            View mView = getLayoutInflater().inflate(R.layout.activity_custom_dialogue,null);
            TextView clickMe = mView.findViewById(R.id.tvClickme);

            alert.setView(mView); // Showing dialig box;
            final  AlertDialog alertDialog = alert.create();
            alertDialog.setCanceledOnTouchOutside(false);
            clickMe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(),CheckWifiConnection.class);
                    startActivity(i);
                }
            });
            alertDialog.show();*/
            }
        });

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!WifiNamePass())
                    return;
                /**Code To Display DialogBox***/
                final AlertDialog.Builder alert = new AlertDialog.Builder(WifiPassward.this);
                View mView = getLayoutInflater().inflate(R.layout.activity_custom_dialogue,null);
                alert.setView(mView); // Showing dialig box;
                final  AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.show();
                /**Code End To Display DialogBox**/

                String myURL = "https://apex.oracle.com/pls/apex/neha_semwal/login/login/";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, myURL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    Toast.makeText(WifiPassward.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    Log.e("TAG", "jsonObject " + jsonObject);
                                    /**Intent For Next Page**/
                                    Intent i = new Intent(getApplicationContext(),CheckWifiConnection.class);
                                    startActivity(i);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Log.e("JSON Parser", "Error parsing data [" + e.getMessage() + "] " + response);
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(WifiPassward.this, error.getMessage(), Toast.LENGTH_LONG).show();
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

    private String apiParameter() {
        String urlString = null;
        try {
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonObject1 = new JSONObject();
            jsonObject.put("API", "wifi_api");
            jsonObject.put("CURD_OPERATION", "I");
            jsonObject.put("wifi_name", etWifiName.getText().toString().trim());
            jsonObject.put("password", etWifiPassword.getText().toString().trim());
            jsonObject1.put("ITEMS", jsonObject);
            urlString = jsonObject1.toString();
            Log.e("TAG", "urlString" + urlString);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("EXCEPTION>>>", "" + String.valueOf(e));
        }
        return urlString;
    }


    private  boolean WifiNamePass(){
        String wifiName = etWifiName.getText().toString().trim();
        String wifiPassword = etWifiPassword.getText().toString().trim();
        if (wifiName.isEmpty()){
            etWifiName.setError("can't be empty");
            return false;
        } else if (wifiPassword.isEmpty() ) {
            etWifiPassword.setError("can't be empty");
            return false;
        } else {
            etWifiName.setError(null);
            etWifiName.setError(null);
            return true;
        }
    }
}
