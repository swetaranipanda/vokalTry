package com.vokal;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;

import com.vokal.adapter.SmsAdapter;
import com.vokal.pojo.ObserverObject;
import com.vokal.pojo.SmsModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.TimeZone;

public class MessageActivity extends AppCompatActivity implements Observer {
    private RecyclerView rcv;
    public static String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initUI();
        setAdapter();
    }

    private void initUI() {
        rcv = findViewById(R.id.rcv);
        rcv.setHasFixedSize(true);
    }

    public void setAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcv.setLayoutManager(linearLayoutManager);
        SmsAdapter smsAdapter = new SmsAdapter(getList(), MessageActivity.this);
        rcv.setAdapter(smsAdapter);
    }

    public List<SmsModel> getList() {
        List<SmsModel> sms = new ArrayList<SmsModel>();
        Uri uriSMSURI = Uri.parse("content://sms/inbox");
        Cursor cur = getContentResolver().query(uriSMSURI, null, null, null, null);
        Log.i("cursorvalue", String.valueOf(cur.getCount()));

        while (cur != null && cur.moveToNext()) {
            String body = cur.getString(cur.getColumnIndexOrThrow("body"));
            String date = cur.getString(cur.getColumnIndex("date"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            long time = 0;
            try {
                time = sdf.parse(date).getTime();
            } catch (ParseException e) {
                time = 0;
            }
            long now = System.currentTimeMillis();

            CharSequence ago = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.HOUR_IN_MILLIS);
            ObserverObject obj = new ObserverObject(String.valueOf(ago));
            obj.addObserver(this);
            SmsModel model = new SmsModel();
            model.body = body;
            model.time = String.valueOf(ago);
            sms.add(model);

        }


        if (cur != null) {
            cur.close();
        }
        return sms;
    }

    @Override
    public void update(Observable observable, Object o) {
        value ="timeShow";
    }
}