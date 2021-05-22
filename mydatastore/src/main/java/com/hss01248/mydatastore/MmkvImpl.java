package com.hss01248.mydatastore;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.getkeepsafe.relinker.ReLinker;
import com.tencent.mmkv.MMKV;
import com.tencent.mmkv.MMKVLogLevel;

import java.util.Set;

/**
 * 无缝迁移
 * https://www.jianshu.com/p/43ce8a7ed62e
 */
public class MmkvImpl extends BaseKV{


     MMKV getKV(String name) {
        MMKV mmkv;
        String key = name+encrypt;
        mmkv = (MMKV) map.get(key);
        if(mmkv != null){
            return mmkv;
        }
        if (TextUtils.isEmpty(name)) {
            mmkv = encrypt ? MMKV.defaultMMKV(MMKV.MULTI_PROCESS_MODE, cryptKey) : MMKV.defaultMMKV(MMKV.MULTI_PROCESS_MODE, null);
        } else {
            mmkv = encrypt ? MMKV.mmkvWithID(name, MMKV.MULTI_PROCESS_MODE, cryptKey) : MMKV.mmkvWithID(name, MMKV.MULTI_PROCESS_MODE);
        }
        if (migrate) {
            //迁移SharedPreferences旧数据
            SharedPreferences sharedPreferences;
            if (TextUtils.isEmpty(name)) {
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            } else {
                sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
            }
            mmkv.importFromSharedPreferences(sharedPreferences);
            sharedPreferences.edit().clear().apply();
        }
        map.put(key,mmkv);
        return mmkv;
    }


    @Override
    public void init(Application app) {
        context = app;
        String root = context.getFilesDir().getAbsolutePath() + "/mmkv";
        if (android.os.Build.VERSION.SDK_INT == 19) {
            MMKV.initialize(root, libName -> ReLinker.loadLibrary(context, libName));
        } else {
            MMKV.initialize(context);
        }
        MMKV.setLogLevel(MMKVLogLevel.LevelDebug);
    }

    @Override
    public double getDouble(String name, String key, double defValue) {
        return getKV(name).decodeDouble(key,defValue);
    }

    @Override
    public void putDouble(String name, String key, double value) {
        getKV(name).encode(key, value);
    }

    @Override
    public Set<String> getStringSet(String name, String key, Set<String> defValues) {
          return getKV(name).decodeStringSet(key,defValues);
    }

    @Override
    public void putStringSet(String name, String key, Set<String> values) {
            getKV(name).encode(key,values);
    }

    @Override
    public String getString(String name, String key, String defValue) {
        return  getKV(name).decodeString(key,defValue);
    }

    @Override
    public void putString(String name, String key, String value) {
        getKV(name).encode(key, value);
    }

    @Override
    public boolean getBoolean(String name, String key, boolean defValue) {
          return  getKV(name).decodeBool(key,defValue);
    }

    @Override
    public void putBoolean(String name, String key, boolean value) {
        getKV(name).encode(key, value);
    }

    @Override
    public void putInt(String name, String key, int value) {
        getKV(name).encode(key, value);
    }

    @Override
    public int getInt(String name, String key, int defValue) {
        return  getKV(name).decodeInt(key,defValue);
    }

    @Override
    public void putFloat(String name, String key, float value) {
        getKV(name).encode(key, value);
    }

    @Override
    public float getFloat(String name, String key, float defValue) {
        return  getKV(name).decodeFloat(key,defValue);
    }

    @Override
    public void putLong(String name, String key, long value) {
        getKV(name).encode(key, value);
    }



    @Override
    public long getLong(String name, String key, long defValue) {
        return  getKV(name).decodeLong(key,defValue);
    }



    @Override
    public void remove(String name, String key) {

    }

    @Override
    public void clear(String name) {

    }

    @Override
    public boolean contains(String name, String key) {
        return false;
    }
}
