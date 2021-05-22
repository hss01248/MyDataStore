package com.hss01248.mydatastore;

import android.content.Context;
import android.content.SharedPreferences;

import com.tencent.mmkv.MMKV;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseKV implements IKV{

    /**
     * 是否加密
     */
   protected boolean encrypt;
    /**
     * 秘钥
     */
    protected String cryptKey;
    /**
     * 是否迁移SharedPreferences旧数据
     */
    protected boolean migrate;
    protected Context context;
    protected Map<String, SharedPreferences> map = new HashMap<>();

   public void setEncrypt(boolean encrypt, String cryptKey){
        this.encrypt = encrypt;
        this.cryptKey = cryptKey;
    }

   public void setMigrate(boolean migrate) {
        this.migrate = migrate;
    }

    protected String getMapKey(String name){
       return name+encrypt;
    }
}
