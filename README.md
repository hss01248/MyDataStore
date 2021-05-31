#  统一sharedprefences和mmkv的使用

* 使用jetpack startup库自动初始化.

* debug时,未加密,非debug时,加密存储.
* 默认使用mmkv,可再次调用KvUtil.init()方法改成sharedprefences
* 实现了mmkv的getAll方法. mmkv存储的key均加上类型标识
* mmkv库so 加载失败,方法链接失败时,自动降级到使用sharedprefences



# api







![image-20210531103836477](https://gitee.com/hss012489/picbed/raw/master/picgo/1622428723009-image-20210531103836477.jpg)





# mmkv和sp的查看:

project根目录里添加:

```groovy
buildscript {
    apply from: 'https://raw.githubusercontent.com/hss01248/flipperUtil/master/remote2.gradle'
  ...
```



![image-20210531104132865](https://gitee.com/hss012489/picbed/raw/master/picgo/1622428892902-image-20210531104132865.jpg)