#  统一sharedprefences和mmkv的使用







* 使用jetpack startup库自动初始化.

* debug时,未加密,非debug时,加密存储.
* 默认使用mmkv,可再次调用KvUtil.init()方法改成sharedprefences
* 实现了mmkv的getAll方法. mmkv存储的key均加上类型标识
* mmkv库so 加载失败,方法链接失败时,自动降级到使用sharedprefences



# api







![image-20210531103836477](https://gitee.com/hss012489/picbed/raw/master/picgo/1622428723009-image-20210531103836477.jpg)

```java
//变化监听
static void addValueChangeCallback(String name,String key,ValueChangeCallback callback)
  
  public interface ValueChangeCallback {

    void onChanged(Object value);
}
```




# gradle

[![](https://jitpack.io/v/hss01248/MyDataStore.svg)](https://jitpack.io/#hss01248/MyDataStore)

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.hss01248:MyDataStore:1.0.1'
	}




# mmkv和sp的查看:

project根目录里添加:

```groovy
buildscript {
    apply from: 'https://raw.githubusercontent.com/hss01248/flipperUtil/master/remote2.gradle'
  ...
```



![image-20210531104132865](https://gitee.com/hss012489/picbed/raw/master/picgo/1622428892902-image-20210531104132865.jpg)



# 一些参考资料

## [想看MMKV, DataStore, 数据库, Sp 性能大比拼，来了](https://mp.weixin.qq.com/s/vimX6bnJDkPbXAqVJCbbfw)

![image-20210531110421918](https://gitee.com/hss012489/picbed/raw/master/picgo/1622430261955-image-20210531110421918.jpg)

![image-20210531110435720](https://gitee.com/hss012489/picbed/raw/master/picgo/1622430275746-image-20210531110435720.jpg)







![image-20210531110504985](https://gitee.com/hss012489/picbed/raw/master/picgo/1622430305011-image-20210531110504985.jpg)

[Android 轻量级存储方案的前世今生（SharedPreferences、MMKV、Jetpack DataStore）](https://www.jianshu.com/p/e2113f501cf9)