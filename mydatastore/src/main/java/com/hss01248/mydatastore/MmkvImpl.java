package com.hss01248.mydatastore;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.getkeepsafe.relinker.ReLinker;
import com.tencent.mmkv.MMKV;
import com.tencent.mmkv.MMKVContentChangeNotification;
import com.tencent.mmkv.MMKVLogLevel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 无缝迁移
 * https://www.jianshu.com/p/43ce8a7ed62e
 *
 * getall方法:
 * https://zhuanlan.zhihu.com/p/357343272
 */
public class MmkvImpl extends BaseKV{
    
    static final String K_STRING = "s";
    static final String K_FLOAT = "f";
    static final String K_DOUBLE = "d";
    static final String K_INT = "i";
    static final String K_LONG = "L";
    static final String K_BOOLEAN = "b";
    static final String K_BYTE_ARRAY = "bs";
    static final String K_STRING_SET = "ss";
    static final String K_SPLIT = "@";

   


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
        MMKV.setLogLevel(KvUtil.isApkInDebug(app) ? MMKVLogLevel.LevelDebug : MMKVLogLevel.LevelWarning);
        if(!KvUtil.isApkInDebug(app)){
            setEncrypt(true,app.getPackageName().replace(".","_"));
        }
    }

    @Override
    public double getDouble(String name, String key, double defValue) {
        return getKV(name).decodeDouble(keyWithType(key,K_DOUBLE),defValue);
    }

    private String keyWithType(String key, String simpleName) {
        return key+K_SPLIT+simpleName;
    }

    @Override
    public void putDouble(String name, String key, double value) {
        getKV(name).encode(keyWithType(key,K_DOUBLE), value);
    }

    @Override
    public Set<String> getStringSet(String name, String key, Set<String> defValues) {
          return getKV(name).decodeStringSet(keyWithType(key,K_STRING_SET),defValues);
    }

    @Override
    public void putStringSet(String name, String key, Set<String> values) {
            getKV(name).encode(keyWithType(key,K_STRING_SET),values);
    }

    @Override
    public String getString(String name, String key, String defValue) {
        return  getKV(name).decodeString(keyWithType(key,K_STRING),defValue);
    }

    @Override
    public void putString(String name, String key, String value) {
        getKV(name).encode(keyWithType(key,K_STRING), value);
    }

    @Override
    public boolean getBoolean(String name, String key, boolean defValue) {
          return  getKV(name).decodeBool(keyWithType(key,K_BOOLEAN),defValue);
    }

    @Override
    public void putBoolean(String name, String key, boolean value) {
        getKV(name).encode(keyWithType(key,K_BOOLEAN), value);
    }

    @Override
    public void putInt(String name, String key, int value) {
        getKV(name).encode(keyWithType(key,K_INT), value);
    }

    @Override
    public int getInt(String name, String key, int defValue) {
        return  getKV(name).decodeInt(keyWithType(key,K_INT),defValue);
    }

    @Override
    public void putFloat(String name, String key, float value) {
        getKV(name).encode(keyWithType(key,K_FLOAT), value);
    }

    @Override
    public float getFloat(String name, String key, float defValue) {
        return  getKV(name).decodeFloat(keyWithType(key,K_FLOAT),defValue);
    }

    @Override
    public void putLong(String name, String key, long value) {
        getKV(name).encode(keyWithType(key,K_LONG), value);
    }



    @Override
    public long getLong(String name, String key, long defValue) {
        return  getKV(name).decodeLong(keyWithType(key,K_LONG),defValue);
    }

    @Override
    public byte[] getBytes(String name, String key, byte[] defValue) {
        return getKV(name).decodeBytes(keyWithType(key,K_BYTE_ARRAY),defValue);
    }

    @Override
    public void putBytes(String name, String key, byte[] value) {
            getKV(name).encode(keyWithType(key,K_BYTE_ARRAY),value);
    }


    @Override
    public void remove(String name, String key) {
         getKV(name).removeValueForKey(guessReallKey(name,key));

    }

    @Override
    public void clear(String name) {
         getKV(name).removeValuesForKeys(getKV(name).allKeys());

    }
    
    String guessReallKey(String name,String key){
        String[] keys = getKV(name).allKeys();
        for (String s : keys) {
            if(s.contains(K_SPLIT)){
                String realKey = s.substring(0,s.lastIndexOf(K_SPLIT));
                if(realKey.equals(key)){
                    return realKey;
                }
            }
        }
        return key;
    }

    @Override
    public boolean contains(String name, String key) {
        return getKV(name).containsKey(guessReallKey(name,key));
    }

    @Override
    public Map<String, ?> getAll(String name) {
        String[] keys = getKV(name).allKeys();
        Map<String, Object> map = new HashMap<>();
        for (String key : keys) {
            if (key.contains(K_SPLIT)) {
                String[] typeList = key.split(K_SPLIT);
                String realKey = key.substring(0, key.lastIndexOf(K_SPLIT));
                String type = typeList[typeList.length - 1];
                if (K_STRING.equals(type)) {
                    map.put(realKey, getKV(name).decodeString(key, ""));
                } else if (K_INT.equals(type)) {
                    map.put(realKey, getKV(name).decodeInt(key, 0));
                } else if (K_LONG.equals(type)) {
                    map.put(realKey, getKV(name).decodeLong(key, 0));
                } else if (K_BOOLEAN.equals(type)) {
                    map.put(realKey, getKV(name).decodeBool(key, false));
                } else if (K_FLOAT.equals(type)) {
                    map.put(realKey, getKV(name).decodeFloat(key, 0));
                } else if (K_DOUBLE.equals(type)) {
                    map.put(realKey, getKV(name).decodeDouble(key, 0));
                } else if (K_BYTE_ARRAY.equals(type)) {
                    map.put(realKey, getKV(name).decodeBytes(key, null));
                }else if (K_STRING_SET.equals(type)) {
                    map.put(realKey, getKV(name).decodeStringSet(key, null));
                }
            }
        }
        return map;
    }

   
}
