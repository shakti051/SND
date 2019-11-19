package com.example.snd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class Home extends AppCompatActivity {
    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    Button btnaddDevice,btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnaddDevice = findViewById(R.id.btnAddDevice);
        tabLayout = findViewById(R.id.tablayout_id);
        appBarLayout = findViewById(R.id.appbarid);
        btnLogout = findViewById(R.id.btnLogout);
        viewPager = findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
            /**Adding Fragments**/
        adapter.AddFragment(new Fragment_AllDevices(),"All Devices");
        adapter.AddFragment(new Fragment_DiningRoom(),"Dining Room");
        adapter.AddFragment(new Fragment_livingRoom(),"Living Room");
        adapter.AddFragment(new Fragment_Others(),"Others");

        /**Adapter Setup**/
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        /**Adding Device**/

        btnaddDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AddDevice.class);
                startActivity(i);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"Please Logout for exit:",Toast.LENGTH_SHORT).show();
    }

}
