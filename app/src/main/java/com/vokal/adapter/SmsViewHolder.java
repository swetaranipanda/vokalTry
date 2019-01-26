package com.vokal.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vokal.R;

public class SmsViewHolder extends RecyclerView.ViewHolder {
    TextView snTxt,smsTxt,timetxt;
    public SmsViewHolder(View itemView) {
        super(itemView);
        snTxt=itemView.findViewById(R.id.sn_txt);
        smsTxt=itemView.findViewById(R.id.sms_txt);
    }
}
