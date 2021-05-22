package com.hss01248.mydatastore;

import android.app.Application;

import java.util.Set;

public class KvUtil {
    static IKV kv;

    public static void init(Application app, IKV kv) {
        KvUtil.kv = kv;
        kv.init(app);
    }


    public static double getDouble(String name, String key, double defValue) {
        return kv.getDouble(name, key, defValue);
    }


    public static void putDouble(String name, String key, double value) {
        kv.putDouble(name, key, value);
    }


    public static Set<String> getStringSet(String name, String key, Set<String> defValues) {
        return kv.getStringSet(name, key, defValues);
    }


    public static void putStringSet(String name, String key, Set<String> values) {
        kv.putStringSet(name, key, values);
    }


    public static String getString(String name, String key, String defValue) {
        return kv.getString(name, key, defValue);
    }


    public static void putString(String name, String key, String value) {
        kv.putString(name, key, value);
    }


    public static boolean getBoolean(String name, String key, boolean defValue) {
        return kv.getBoolean(name, key, defValue);
    }


    public static void putBoolean(String name, String key, boolean value) {
        kv.putBoolean(name, key, value);
    }


    public static void putInt(String name, String key, int value) {
        kv.putInt(name, key, value);
    }


    public static int getInt(String name, String key, int defValue) {
        return kv.getInt(name, key, defValue);
    }


    public static void putFloat(String name, String key, float value) {
        kv.putFloat(name, key, value);
    }


    public static float getFloat(String name, String key, float defValue) {
        return kv.getFloat(name, key, defValue);
    }


    public static void putLong(String name, String key, long value) {
        kv.putLong(name, key, value);
    }


    public static long getLong(String name, String key, long defValue) {
        return kv.getLong(name, key, defValue);
    }


    public static void putObj(String name, String key, Object value) {
        kv.putObj(name, key, value);
    }


    public static <T> T getObj(String name, String key, Class<T> clazz, T defValue) {
        return kv.getObj(name, key, clazz, defValue);
    }


    public static void remove(String name, String key) {
        kv.remove(name, key);
    }


    public static void clear(String name) {
        kv.clear(name);
    }


    public static boolean contains(String name, String key) {
        return kv.contains(name, key);
    }


    public static double getDouble(String key, double defValue) {
        return kv.getDouble(key, defValue);
    }


    public static void putDouble(String key, double value) {
        kv.putDouble(key, value);
    }


    public static String getString(String key, String defValue) {
        return kv.getString(key, defValue);
    }


    public static void putString(String key, String value) {
        kv.putString(key, value);
    }


    public static Set<String> getStringSet(String key, Set<String> defValues) {
        return kv.getStringSet(key, defValues);
    }


    public static void putStringSet(String key, Set<String> values) {
        kv.putStringSet(key, values);
    }


    public static boolean getBoolean(String key, boolean defValue) {
        return kv.getBoolean(key, defValue);
    }


    public static void putBoolean(String key, boolean value) {
        kv.putBoolean(key, value);
    }


    public static void putInt(String key, int value) {
        kv.putInt(key, value);
    }


    public static int getInt(String key, int defValue) {
        return kv.getInt(key, defValue);
    }


    public static void putFloat(String key, float value) {
        kv.putFloat(key, value);
    }


    public static float getFloat(String key, float defValue) {
        return kv.getFloat(key, defValue);
    }


    public static void putLong(String key, long value) {
        kv.putLong(key, value);
    }


    public static long getLong(String key, long defValue) {
        return kv.getLong(key, defValue);
    }


    public static void putObj(String key, Object value) {
        kv.putObj(key, value);
    }


    public static <T> T getObj(String key, Class<T> clazz, T defValue) {
        return kv.getObj(key, clazz, defValue);
    }
}
