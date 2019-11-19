package com.example.snd;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class AddWifiAdapter extends RecyclerView.Adapter<AddWifiAdapter.ViewHolder> {
    private ArrayList<AddWifiModel> pairWifi;
    Context context;

    public AddWifiAdapter(ArrayList<AddWifiModel> pairWifi, Context context) {
        this.pairWifi = pairWifi;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivWifi,ivRightArrow;
        TextView tvWifiDevice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivWifi = itemView.findViewById(R.id.ivWifiLogo);
            tvWifiDevice = itemView.findViewById(R.id.tvWifiDevice);
            ivRightArrow = itemView.findViewById(R.id.ivRightArrow);
            ivRightArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context,WifiPassward.class);
                    context.startActivity(i);
                }
            });
        }
    }

    @NonNull
    @Override
    public AddWifiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_connect_wifi,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AddWifiAdapter.ViewHolder viewHolder, int i) {
        viewHolder.itemView.setTag(pairWifi.get(i));
        viewHolder.tvWifiDevice.setText(pairWifi.get(i).getWifiDevice());

        if (pairWifi.get(i).getWifi().equals("Yourwifi"))
            viewHolder.ivWifi.setImageResource(R.drawable.wifilogo);
        else if (pairWifi.get(i).getRightArrow().equals("RightArrow"))
            viewHolder.ivRightArrow.setImageResource(R.drawable.rightarrow);
    }

    @Override
    public int getItemCount() {
        return pairWifi.size();
    }
}
