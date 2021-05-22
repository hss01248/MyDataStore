package com.hss01248.mydatastore;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;


/**
 * Created by huangshuisheng on 2017/12/13.
 */
@Deprecated
public class SpUtil {

    static Application app;

    public static void init(Application application){
        app = application;
    }

    private static final String SP_FILE_NAME = "SpUtilAD";

    public static void putLong(String key,long val){
        getSP().edit().putLong(key,val)
            .apply();
    }

    public static long getLong(String key,long defVal){
        return getSP().getLong(key,defVal);
    }



    public static void putBoolean(String key,boolean val){
        getSP().edit().putBoolean(key,val).apply();
    }

    public static boolean getBoolean(String key,boolean defVal){
        return getSP().getBoolean(key,defVal);
    }

    public static void putInt(String key,int val){
        getSP().edit().putInt(key,val).apply();
    }

    public static long getInt(String key,int defVal){
        return getSP().getInt(key,defVal);
    }

    public static void putString(String key,String val){
        getSP().edit().putString(key,val).apply();
    }

    public static String getString(String key,String defVal){
        return getSP().getString(key,defVal);
    }

    public static void putFloat(String key,float val){
        getSP().edit().putFloat(key,val).apply();
    }

    public static float getFloat(String key,float defVal){
        return getSP().getFloat(key,defVal);
    }

    public static void putDouble(String key,double val){
        String dstr = val+"";
        getSP().edit().putString(key,dstr).apply();
    }

    public static double getDouble(String key,double defVal){
        String dstr =  getSP().getString(key,defVal+"");
        Log.i("sp","getDouble:"+dstr);
        if(!TextUtils.isEmpty(dstr)){
            return Double.parseDouble(dstr);
        }else {
            return defVal;
        }

    }



    private static SharedPreferences getSP(){
        return app.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
    }
}
