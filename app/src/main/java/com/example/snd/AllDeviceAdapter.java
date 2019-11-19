package com.example.snd;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllDeviceAdapter extends RecyclerView.Adapter<AllDeviceAdapter.ViewHolder> {
    private ArrayList<AllDevice> myHomeDevices;
    Context activity;
    public AllDeviceAdapter(Context activity, ArrayList<AllDevice> myHomeDevices) {
        this.myHomeDevices = myHomeDevices;
        this.activity = activity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivDeviceLinking,ivGreencircle;
        TextView tvDevicename,tvDeviceLocation;
        Button btnUnpair,btnPush,btnPair,toggleButton;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
        ivDeviceLinking = itemView.findViewById(R.id.ivDeviceLinking);
        ivGreencircle = itemView.findViewById(R.id.ivGreenCircle);
        tvDevicename = itemView.findViewById(R.id.tvDeviceName);
        tvDeviceLocation = itemView.findViewById(R.id.tvDeviceLocation);
        btnUnpair = itemView.findViewById(R.id.btnUnpair);
        btnPush = itemView.findViewById(R.id.btnPush);
        btnPair = itemView.findViewById(R.id.btnpair);
        btnPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity,ConnectWifi.class);
                activity.startActivity(i);
            }
        });


        btnUnpair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(activity);
                View mView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.activity_unpair__dialogue,null);
                Button btnCanel = mView.findViewById(R.id.btnCancel);
                Button btnOk = mView.findViewById(R.id.btnOk);
                TextView tvUnPair = mView.findViewById(R.id.tvUnpair);
                TextView tvUnPairCit = mView.findViewById(R.id.tvUnpaircit);
                tvUnPair.setText("UNPAIR CIT0001");
                tvUnPairCit.setText("Do You Want To Unpair CIT0001");

                alert.setView(mView); // Showing dialig box;
                final  AlertDialog alertDialog = alert.create();


                alertDialog.setCanceledOnTouchOutside(false);
                btnCanel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btnUnpair.setVisibility(View.GONE);
                        btnPair.setVisibility(View.VISIBLE);
                        alertDialog.dismiss();
                        Toast.makeText(activity,"Unpair Successfully!!",Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.show();
            }
        });

        btnPair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(activity);
                View mView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.activity_unpair__dialogue,null);
                Button btnCanel = mView.findViewById(R.id.btnCancel);
                Button btnOk = mView.findViewById(R.id.btnOk);
                alert.setView(mView); // Showing dialig box;
                final  AlertDialog alertDialog = alert.create();
                TextView tvUnPair = mView.findViewById(R.id.tvUnpair);
                TextView tvUnPairCit = mView.findViewById(R.id.tvUnpaircit);
                tvUnPair.setText("PAIR CIT0001");

                tvUnPairCit.setText("Do You Want To Pair CIT0001");

                alertDialog.setCanceledOnTouchOutside(false);
                btnCanel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btnPair.setVisibility(View.INVISIBLE);
                        btnUnpair.setVisibility(View.VISIBLE);
                        alertDialog.dismiss();
                        Toast.makeText(activity,"pair Successfully!!",Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.show();
            }
        });

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_alldevice,viewGroup,false);
        return new  ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AllDeviceAdapter.ViewHolder viewHolder, int i) {
        viewHolder.itemView.setTag(myHomeDevices.get(i));
        viewHolder.tvDeviceLocation.setText(myHomeDevices.get(i).getRoomType());
        viewHolder.tvDevicename.setText(myHomeDevices.get(i).getDeviceName());
        viewHolder.btnUnpair.setText(myHomeDevices.get(i).getUnpair());
        viewHolder.btnPush.setText(myHomeDevices.get(i).getPush());

        if (myHomeDevices.get(i).getDevice().equals("deviceLinking")){
          viewHolder.ivDeviceLinking.setImageResource(R.drawable.device_linking);
        }

        if (myHomeDevices.get(i).getSymbolType().equals("greenButton")){
            viewHolder.ivGreencircle.setImageResource(R.drawable.greencircle);
        }else if (myHomeDevices.get(i).getSymbolType().equals("redButton")){
            viewHolder.ivGreencircle.setImageResource(R.drawable.redcircle);
        }
    }

    @Override
    public int getItemCount() {
        return myHomeDevices.size();
    }
}
