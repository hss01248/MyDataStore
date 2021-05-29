package com.hss01248.mydatastore;

import android.app.Application;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.Map;
import java.util.Set;

public interface IKV {

    void init(Application app);

    static Gson gson = new Gson();
    double getDouble(String name, String key, double defValue);

    void putDouble(String name, String key,double value);

    Set<String> getStringSet(String name, String key, Set<String> defValues);

    void putStringSet(String name, String key, Set<String> values);

    String getString(String name, String key, String defValue);

    void putString(String name, String key, String value);

    boolean getBoolean(String name, String key, boolean defValue);

    void putBoolean(String name, String key, boolean value);

    void putInt(String name, String key, int value);

    int getInt(String name, String key, int defValue);

    void putFloat(String name, String key, float value);

    float getFloat(String name, String key, float defValue);

    void putLong(String name, String key, long value);

    long getLong(String name, String key, long defValue);

    byte[]  getBytes(String name, String key, byte[] defValue);

    void putBytes(String name, String key, byte[] value);

    default byte[] getBytes( String key, byte[] defValue){
        return getBytes("",key,defValue);
    }

   default void putBytes( String key, byte[] value){
        putBytes("",key,value);
   }

   default void putObj(String name, String key, Object value){
       try {
           putString(name,key,gson.toJson(value));
       }catch (Throwable throwable){
           throwable.printStackTrace();
       }

    }

   default  <T> T  getObj(String name, String key, Class<T> clazz,T defValue){
       String json = getString(name,key,"");
       if(TextUtils.isEmpty(json)){
           return defValue;
       }
       try {
           T obj = gson.fromJson(json,clazz);
           if(obj == null){
               return defValue;
           }
           return obj;
       }catch (Throwable throwable){
           throwable.printStackTrace();
           return defValue;
       }
    }







    void remove(String name, String key);

    void clear(String name);

    boolean contains(String name, String key);

    Map<String, ?> getAll(String name);

    default void remove( String key){
        remove("",key);
    }

    default void clear(){
        clear("");
    }

   default boolean contains( String key){
        return contains("",key);
   }

   default Map<String, ?> getAll(){
        return getAll("");
   }







   default double getDouble( String key, double defValue){
       return getDouble("",key,defValue);
   }

    default void putDouble( String key, double value){
       putDouble("",key, value);
    }

    default String getString( String key, String defValue){
       return getString("",key,defValue);
    }

    default void putString( String key, String value){
       putString("",key,value);
    }

   default Set<String> getStringSet( String key, Set<String> defValues){
      return getStringSet("",key,defValues);
   }

   default void putStringSet( String key, Set<String> values){
       putStringSet("",key,values);
   }

    default boolean getBoolean( String key, boolean defValue){
       return getBoolean("",key,defValue);
    }

    default void putBoolean( String key, boolean value){
       putBoolean("",key,value);
    }

    default void putInt( String key, int value){
       putInt("",key,value);
    }

    default int getInt( String key, int defValue){
       return getInt("",key,defValue);
    }

    default void putFloat( String key, float value){
       putFloat("",key,value);
    }

    default float getFloat( String key, float defValue){
       return getFloat("",key,defValue);
    }

    default void putLong( String key, long value){
       putLong("",key,value);
    }

    default long getLong( String key, long defValue){
       return getLong("",key,defValue);
    }

    default void putObj( String key, Object value){
       putObj("",key,value);
    }

    default  <T> T  getObj( String key, Class<T> clazz,T defValue){
       return getObj("",key,clazz,defValue);
    }
}
