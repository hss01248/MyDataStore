package com.hss01248.mydatastoredemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.hss01248.mydatastore.KvUtil;
import com.hss01248.mydatastore.MmkvImpl;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //KvUtil.init(getApplication(),new MmkvImpl());
        setContentView(R.layout.activity_main);

        KvUtil.putString("uuid","89dsjifjeu8hfueismfle");
        KvUtil.putDouble("deviceid",78474D);

        Log.w("dd","uuid:"+KvUtil.getString("uuid",""));
        Log.w("dd","uuid:"+KvUtil.getDouble("deviceid",0D));
    }
}