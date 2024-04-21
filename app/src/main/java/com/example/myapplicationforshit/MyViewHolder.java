package com.example.myapplicationforshit;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

public class MyViewHolder extends ViewHolder {
    public Button B;
    public TextView T;
    public TextView t1;
    public TextView t2;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        T= (TextView) itemView.findViewById(R.id.title);
        B = (Button) itemView.findViewById(R.id.icon);
        t1=(TextView) itemView.findViewById(R.id.sub1);
        t2=(TextView) itemView.findViewById(R.id.sub2);

    }
}
