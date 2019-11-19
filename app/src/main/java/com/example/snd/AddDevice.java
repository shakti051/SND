package com.example.snd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddDevice extends AppCompatActivity {

    RecyclerView addDevice_Recycler;
    RecyclerView.Adapter addDevice_Adapter;
    RecyclerView.LayoutManager addDevice_LayoutManager;
    ArrayList<AddDeviceModel> pairDevices;

    private List<ScanResult> results;
    private WifiManager wifiManager;


    @SuppressLint("WifiManagerLeak")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);
        /**Set ActionSupportBar in Center**/
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.support_action_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);/**For Getting back button**/

        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);


        /**Recyclerview Coding**/
        addDevice_Recycler = findViewById(R.id.addDevice_Recyclerview);
        addDevice_Recycler.setHasFixedSize(true);
        addDevice_LayoutManager = new LinearLayoutManager(this);
        addDevice_Recycler.setLayoutManager(addDevice_LayoutManager);
        pairDevices = new ArrayList<AddDeviceModel>();

        /* pairDevices.add(new AddDeviceModel("YourWifi","ABC12334","Pair Device"));
        pairDevices.add(new AddDeviceModel("YourWifi","ABC12334","Pair Device"));
        pairDevices.add(new AddDeviceModel("YourWifi","ABC12334","Pair Device"));
        pairDevices.add(new AddDeviceModel("YourWifi","ABC12334","Pair Device"));
        addDevice_Adapter = new AddDeviceAdapter(this, pairDevices);
        addDevice_Recycler.setAdapter(addDevice_Adapter);
*/

       /**Wifi Coding**/
        wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (!wifiManager.isWifiEnabled()){
            Toast.makeText(this,"Wifi Disabled.. You need to enable it.",Toast.LENGTH_SHORT).show();
            wifiManager.setWifiEnabled(true);
        }
        addDevice_Adapter = new AddDeviceAdapter(this,pairDevices);
        addDevice_Recycler.setAdapter(addDevice_Adapter);
        scanWifi();
    }

    private void scanWifi(){
        pairDevices.clear();
        registerReceiver(wifiReceiver,new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.startScan();
        Toast.makeText(this,"Scanning wifi.....",Toast.LENGTH_SHORT).show();
    }

    BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            results = wifiManager.getScanResults();
            unregisterReceiver(this);
            for (ScanResult scanResult:results){
                pairDevices.add(new AddDeviceModel("YourWifi",scanResult.SSID,"Pair Device"));
                addDevice_Adapter.notifyDataSetChanged();
            }
        }
    };
}
