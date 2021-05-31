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





# [想看MMKV, DataStore, 数据库, Sp 性能大比拼，来了](https://mp.weixin.qq.com/s/vimX6bnJDkPbXAqVJCbbfw)

![image-20210531110421918](https://gitee.com/hss012489/picbed/raw/master/picgo/1622430261955-image-20210531110421918.jpg)

![image-20210531110435720](https://gitee.com/hss012489/picbed/raw/master/picgo/1622430275746-image-20210531110435720.jpg)







![image-20210531110504985](https://gitee.com/hss012489/picbed/raw/master/picgo/1622430305011-image-20210531110504985.jpg)



