-keep class com.hss01248.mydatastore.**{*;}
# 保留 MMKV 相关类不被混淆
-keep class com.tencent.mmkv.** { *; }
-keep interface com.tencent.mmkv.** { *; }