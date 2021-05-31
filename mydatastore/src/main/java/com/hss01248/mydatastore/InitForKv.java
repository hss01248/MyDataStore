package com.hss01248.mydatastore;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.startup.Initializer;

import java.util.ArrayList;
import java.util.List;

public class InitForKv implements Initializer<IKV> {
    @NonNull
    @Override
    public IKV create(@NonNull Context context) {
        IKV kv = new MmkvImpl();
        if(context instanceof Application){
            KvUtil.init((Application) context,kv);
            Log.i("init","KvUtil.init :"+kv);
        }else {
            Log.e("init","context not application:"+context);
        }

        return kv;
    }

    @NonNull
    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        return new ArrayList<>();
    }
}
