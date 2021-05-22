package com.hss01248.mydatastore;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class SpKvImpl extends BaseKV{





    private  SharedPreferences getSP(String name){
        return context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    @Override
    public void init(Application app) {
       context = app;
    }

    @Override
    public double getDouble(String name, String key, double defValue) {
        String str = getSP(name).getString(key,defValue+"");
        try {
            return Double.parseDouble(str);
        }catch (Throwable throwable){
            throwable.printStackTrace();
            return defValue;
        }
    }

    @Override
    public void putDouble(String name, String key, double value) {
        getSP(name).edit().putString(key, value+"").apply();
    }

    @Override
    public Set<String> getStringSet(String name, String key, Set<String> defValues) {
        return  getSP(name).getStringSet(key,defValues);
    }

    @Override
    public void putStringSet(String name, String key, Set<String> values) {
        getSP(name).edit().putStringSet(key, values).apply();
    }

    @Override
    public String getString(String name, String key, String defValue) {
        return getSP(name).getString(key, defValue);
    }

    @Override
    public void putString(String name, String key, String value) {
        getSP(name).edit().putString(key, value).apply();
    }

    @Override
    public boolean getBoolean(String name, String key, boolean defValue) {
        return getSP(name).getBoolean(key, defValue);
    }

    @Override
    public void putBoolean(String name, String key, boolean value) {
        getSP(name).edit().putBoolean(key, value).apply();
    }

    @Override
    public void putInt(String name, String key, int value) {
        getSP(name).edit().putInt(key, value).apply();
    }

    @Override
    public int getInt(String name, String key, int defValue) {
        return getSP(name).getInt(key, defValue);
    }

    @Override
    public void putFloat(String name, String key, float value) {
        getSP(name).edit().putFloat(key, value).apply();
    }

    @Override
    public float getFloat(String name, String key, float defValue) {
        return getSP(name).getFloat(key, defValue);
    }

    @Override
    public void putLong(String name, String key, long value) {
        getSP(name).edit().putLong(key, value).apply();
    }

    @Override
    public long getLong(String name, String key, long defValue) {
        return getSP(name).getLong(key, defValue);
    }



    @Override
    public void remove(String name, String key) {

    }

    @Override
    public void clear(String name) {

    }

    @Override
    public boolean contains(String name, String key) {
        return  getSP(name).contains(key);
    }
}
