package com.example.snd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddDeviceAdapter extends RecyclerView.Adapter<AddDeviceAdapter.ViewHolder> {
    private ArrayList<AddDeviceModel> pairDevices;
    Context context;

    public AddDeviceAdapter( Context context,ArrayList<AddDeviceModel> pairDevices) {
        this.context = context;
        this.pairDevices = pairDevices;
        Log.e("TAG","data"+pairDevices);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivWifi;
        TextView tvNewDevice;
        Button btnPairDevice;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            ivWifi = itemView.findViewById(R.id.ivWifiLogo);
            tvNewDevice = itemView.findViewById(R.id.tvNewDevice);
            btnPairDevice = itemView.findViewById(R.id.btnPairDevice);
            btnPairDevice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                         final AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    View mView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.activity_pair_dialogue,null);
                    TextView tvClickme = mView.findViewById(R.id.tvClickme);
                    alert.setView(mView); // Showing dialig box;
                    final  AlertDialog alertDialog = alert.create();
                    alertDialog.setCanceledOnTouchOutside(false);
                    tvClickme.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(context,CheckPairedConnection.class);
                            context.startActivity(i);
                        }
                    });
                    alertDialog.show();
                }
            });
        }
    }

    @Override
    public AddDeviceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_add_device,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AddDeviceAdapter.ViewHolder viewHolder,final int i) {
        viewHolder.itemView.setTag(pairDevices.get(i));
        Log.e("TAG","dataZZZZZZZ"+pairDevices);
        viewHolder.tvNewDevice.setText(pairDevices.get(i).getDeviceName());
        viewHolder.btnPairDevice.setText(pairDevices.get(i).getPairButton());

        if (pairDevices.get(i).getWifi().equals("YourWifi")){
            viewHolder.ivWifi.setImageResource(R.drawable.wifilogo);
        }
    }

    @Override
    public int getItemCount() {
        return pairDevices.size();
    }

}
