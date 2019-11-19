package com.example.snd;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Fragment_AllDevices extends Fragment {
    View view;
    RecyclerView alldevice_recyclerView;
    RecyclerView.Adapter alldevice_Adapter;
    RecyclerView.LayoutManager alldevice_layoutManager;
    ArrayList<AllDevice> myHomeDevices;
    private List<ScanResult> results;
    private WifiManager wifiManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.alldevice_fragment,container,false);
        alldevice_recyclerView = (RecyclerView)view.findViewById(R.id.alldevice_recyclerview);
        alldevice_recyclerView.setHasFixedSize(true);
        alldevice_layoutManager = new LinearLayoutManager(getContext());
        alldevice_recyclerView.setLayoutManager(alldevice_layoutManager);

        wifiManager = (WifiManager)getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (!wifiManager.isWifiEnabled()){
            Toast.makeText(getContext(),"Wifi Disabled.. You need to enable it.", Toast.LENGTH_SHORT).show();
            wifiManager.setWifiEnabled(true);
        }
        alldevice_Adapter = new AllDeviceAdapter(getContext(), myHomeDevices);
        alldevice_recyclerView.setAdapter(alldevice_Adapter);
        scanWifi();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myHomeDevices = new ArrayList<AllDevice>();
        /*myHomeDevices.add(new AllDevice("deviceLinking","CIT0001","Living Room","Unpair","Push","greenButton"));
        myHomeDevices.add(new AllDevice("deviceLinking","CIT0002","Dining Room","Unpair","Push","redButton"));
        myHomeDevices.add(new AllDevice("deviceLinking","CIT0003","Living Room","Unpair","Push","greenButton"));
        myHomeDevices.add(new AllDevice("deviceLinking","CIT0004","Dining Room","Unpair","Push","redButton"));
        alldevice_Adapter = new AllDeviceAdapter(getContext(),myHomeDevices);*/
    }

    private void scanWifi(){
        myHomeDevices.clear();
        registerReceiver(wifiReceiver,new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.startScan();
        Toast.makeText(getContext(),"Scanning wifi.....",Toast.LENGTH_SHORT).show();
    }

    public Intent registerReceiver(
            BroadcastReceiver receiver, IntentFilter filter) {
        return getContext().registerReceiver(receiver, filter);
    }

    public void unregisterReceiver(BroadcastReceiver receiver) {
        getContext().unregisterReceiver(receiver);
    }

    BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            results = wifiManager.getScanResults();
            unregisterReceiver(this);
            for (ScanResult scanResult:results){
                   //     if (scanResult.SSID.startsWith("C")){
                myHomeDevices.add(new AllDevice("deviceLinking",scanResult.SSID,"Dining Room","Unpair","Push","greenButton"));
                alldevice_Adapter.notifyDataSetChanged();
            }
        }
    };
}
