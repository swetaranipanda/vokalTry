package com.vokal.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vokal.MessageActivity;
import com.vokal.R;
import com.vokal.pojo.SmsModel;

import java.util.ArrayList;
import java.util.List;

public class SmsAdapter extends RecyclerView.Adapter<SmsViewHolder> {
    List<SmsModel> smsList=new ArrayList<>();
    Context ctx;

    public  SmsAdapter(List<SmsModel> list,Context ctx){
        this.smsList=list;
        this.ctx=ctx;

    }



    @NonNull
    @Override
    public SmsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view=LayoutInflater.from(ctx).inflate(R.layout.list_item,parent,false);

        return new SmsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SmsViewHolder holder, int position) {
        holder.snTxt.setText(position+1);
        holder.smsTxt.setText(smsList.get(position).body);
        if (MessageActivity.value.equalsIgnoreCase("timeShow")){
            holder.timetxt.setVisibility(View.VISIBLE);
            holder.timetxt.setText(smsList.get(position).time+" hours ago : ");

        }else {
            holder.timetxt.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return smsList.size();
    }
}
