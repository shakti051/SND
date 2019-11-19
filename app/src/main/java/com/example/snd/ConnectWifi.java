package com.example.snd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ConnectWifi extends AppCompatActivity {
    RecyclerView addWifi_Recycler;
    RecyclerView.Adapter addWifi_Adapter;
    RecyclerView.LayoutManager addWifi_LayoutManager;
    ArrayList<AddWifiModel> pairWifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_wifi);
        /**Set ActionSupportBar in Center**/
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.support_wifi_actionbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);/**For Getting back button**/
        /**RecyclerView coding**/
        addWifi_Recycler = findViewById(R.id.addwifi_Recyclerview);
        addWifi_Recycler.setHasFixedSize(true);
        addWifi_LayoutManager = new LinearLayoutManager(this);
        addWifi_Recycler.setLayoutManager(addWifi_LayoutManager);
        pairWifi = new ArrayList<AddWifiModel>();
        pairWifi.add(new AddWifiModel("Yourwifi","MAXX","RightArrow"));
        pairWifi.add(new AddWifiModel("Yourwifi","AIRTEL","RightArrow"));
        pairWifi.add(new AddWifiModel("Yourwifi","SIFY","RightArrow"));
        pairWifi.add(new AddWifiModel("Yourwifi","NEXTRA","RightArrow"));
        addWifi_Adapter = new AddWifiAdapter(pairWifi,this);
        addWifi_Recycler.setAdapter(addWifi_Adapter);
    }
}
