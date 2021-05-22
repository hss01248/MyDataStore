package com.hss01248.mydatastore;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.getkeepsafe.relinker.ReLinker;
import com.tencent.mmkv.MMKV;
import com.tencent.mmkv.MMKVLogLevel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Deprecated
public class MMKVUtils {
    private volatile static MMKVUtils instance = new MMKVUtils();
    private static Context mContext;
    /**
     * 是否加密
     */
    private static boolean encrypt;
    /**
     * 秘钥
     */
    private static String cryptKey;
    /**
     * 是否迁移SharedPreferences旧数据
     */
    private static boolean migrate;
    static Map<String,MMKV> map = new HashMap<>();

    private MMKVUtils() {
    }

    public static MMKVUtils get() {
        if (instance == null) {
            synchronized (MMKVUtils.class) {
                if (instance == null) {
                    instance = new MMKVUtils();
                }
            }
        }
        return instance;
    }

    public static void init(Context context) {
        mContext = context;
        String root = context.getFilesDir().getAbsolutePath() + "/mmkv";
        if (android.os.Build.VERSION.SDK_INT == 19) {
            MMKV.initialize(root, libName -> ReLinker.loadLibrary(context, libName));
        } else {
            MMKV.initialize(context);
        }
    }

    /**
     * 日志等级
     */
    public static void setLogLevel(MMKVLogLevel level) {
        MMKV.setLogLevel(level);
    }

    /**
     * 是否开启加密解密
     *
     * @param encrypt  是否开启
     * @param cryptKey 秘钥
     */
    public static void setEncrypt(boolean encrypt, String cryptKey) {
        MMKVUtils.encrypt = encrypt;
        MMKVUtils.cryptKey = cryptKey;
    }

    /**
     * 是否迁移旧数据
     */
    public static void setMigrate(boolean migrate) {
        MMKVUtils.migrate = migrate;
    }

      MMKV getMMKV() {
        return getMMKV(null);
    }

     MMKV getMMKV(String name) {
        MMKV mmkv;
        String key = name+encrypt;
        mmkv = map.get(key);
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
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            } else {
                sharedPreferences = mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
            }
            mmkv.importFromSharedPreferences(sharedPreferences);
            sharedPreferences.edit().clear().apply();
        }
        map.put(key,mmkv);
        return mmkv;
    }

    //String Set类型---------------------------------------------------------------------------------
    public static Set<String> getStringSet(String key) {
        return get().getMMKV().getStringSet(key, new HashSet<>());
    }

    public static  Set<String> getStringSet(String key, Set<String> defValues) {
        return get().getMMKV().getStringSet(key, defValues);
    }

    public static  Set<String> getStringSet(String name, String key, Set<String> defValues) {
        return get().getMMKV(name).getStringSet(key, defValues);
    }

    public static  void putStringSet(String key, Set<String> values) {
        get().getMMKV().putStringSet(key, values);
    }

    public static  void putStringSet(String name, String key, Set<String> values) {
       get().getMMKV(name).putStringSet(key, values);
    }

    //Double类型---------------------------------------------------------------------------------
    public static  void putDouble(String key, double value) {
        get().getMMKV().encode(key, value);
    }

    public static  double getDouble(String key) {
        return get().getMMKV().decodeDouble(key);
    }

    public static  double getDouble(String key, double defValue) {
        return get().getMMKV().decodeDouble(key, defValue);
    }

    public static  double getDouble(String name, String key, double defValue) {
        return get().getMMKV(name).decodeDouble(key, defValue);
    }
    //byte[]类型---------------------------------------------------------------------------------

    public static  void putByte(String key, byte[] value) {
        get().getMMKV().encode(key, value);
    }

    public static  byte[] getBytes(String key) {
        return get().getMMKV().decodeBytes(key);
    }

    public static  byte[] getBytes(String key, byte[] defValue) {
        return get().getMMKV().decodeBytes(key, defValue);
    }

    public static  byte[] getBytes(String name, String key, byte[] defValue) {
        return get().getMMKV(name).decodeBytes(key, defValue);
    }
    //String类型---------------------------------------------------------------------------------

    public static  String getString(String key) {
        return get().getMMKV().getString(key, "");
    }

    public static  String getString(String key, String defValue) {
        return get().getMMKV().getString(key, defValue);
    }

    public static  String getString(String name, String key, String defValue) {
        return get().getMMKV(name).getString(key, defValue);
    }

    public static  void putString(String key, String value) {
        get().getMMKV().putString(key, value);
    }

    public static  void putString(String name, String key, String value) {
        get().getMMKV(name).putString(key, value);
    }

    //Boolean类型-----------------------------------------------------------------------------------------------
    public static  boolean getBoolean(String key) {
        return get().getMMKV().getBoolean(key, false);
    }

    public static  boolean getBoolean(String key, boolean defValue) {
        return get().getMMKV().getBoolean(key, defValue);
    }

    public static  boolean getBoolean(String name, String key, boolean defValue) {
        return get().getMMKV(name).getBoolean(key, defValue);
    }

    public static  void putBoolean(String key, boolean value) {
        get().getMMKV().putBoolean(key, value);
    }

    public static  void putBoolean(String name, String key, boolean value) {
        get().getMMKV(name).putBoolean(key, value);
    }

    //Int类型-----------------------------------------------------------------------------------------------
    public static  void putInt(String key, int value) {
        get().getMMKV().putInt(key, value);
    }

    public static  void putInt(String name, String key, int value) {
        get().getMMKV(name).putInt(key, value);
    }

    public static  int getInt(String key) {
        return get().getMMKV().getInt(key, 0);
    }

    public static  int getInt(String key, int defValue) {
        return get().getMMKV().getInt(key, defValue);
    }

    public static  int getInt(String name, String key, int defValue) {
        return get().getMMKV(name).getInt(key, defValue);
    }

    //Float类型-----------------------------------------------------------------------------------------------
    public static  void putFloat(String key, float value) {
        get().getMMKV().putFloat(key, value);
    }

    public static  void putFloat(String name, String key, float value) {
        get().getMMKV(name).putFloat(key, value);
    }

    public static  float getFloat(String key) {
        return get().getMMKV().getFloat(key, 0f);
    }

    public static  float getFloat(String key, float defValue) {
        return get().getMMKV().getFloat(key, defValue);
    }

    public static  float getFloat(String name, String key, float defValue) {
        return get().getMMKV(name).getFloat(key, defValue);
    }

    //Long类型-----------------------------------------------------------------------------------------------
    public static  void putLong(String key, long value) {
        get().getMMKV().putLong(key, value);
    }

    public static  void putLong(String name, String key, long value) {
        get().getMMKV(name).putLong(key, value);
    }

    public static  long getLong(String key) {
        return get().getMMKV().getLong(key, 0L);
    }

    public static  long getLong(String key, long defValue) {
        return get().getMMKV().getLong(key, defValue);
    }

    public static  long getLong(String name, String key, long defValue) {
        return get().getMMKV(name).getLong(key, defValue);
    }

    //其他方法-----------------------------------------------------------------------------------------------
    public static  void remove(String key) {
        get().getMMKV().remove(key);
    }

    public static  void remove(String name, String key) {
        get().getMMKV(name).remove(key);
    }

    public static  void clear() {
        get().getMMKV().clear();
    }

    public static  void clear(String name) {
        get().getMMKV(name).clear();
    }

    public static  boolean contains(String key) {
        return get().getMMKV().contains(key);
    }

    public static  boolean contains(String name, String key) {
        return get().getMMKV(name).contains(key);
    }
}
