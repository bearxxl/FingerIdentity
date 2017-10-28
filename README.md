<p align="center">
<font size="30" color="#123456">指纹识别</font>
</p>

# Latest Version
##### _OCT 23, 2017_ - [v1.0.0](#1.0.0)
支持Android原生6.0及以上+魅族+三星


<!-- MarkdownTOC -->
<img src="screenshots/screenshots1.png" height="400" alt="Screenshot"/>
<img src="screenshots/screenshots2.png" height="400" alt="Screenshot"/> 
<img src="screenshots/screenshots3.png" height="400" alt="Screenshot"/>

- [Quick Start Guide](#quick-start-guide)
    - [准备工作](#准备工作)
    - [清单文件](#清单文件)
    - [调用步骤](#调用步骤)
    - [混淆文件](#混淆文件)
- [I want to know more!](#i-want-to-know-more)
- [支持](#支持)

<!-- /MarkdownTOC -->

<a name="quick-start-guide"></a>
# Quick Start Guide


<a name="准备工作"></a>
## 准备工作

### 导入依赖包

 1.app/build.gradle下添加依赖
 
     compile 'com.xuelianx.finger:fingerlib:1.0.0'




<a name="调用步骤"></a>
## 调用步骤

### Initialization

 
```java
//初始化
  FingerFragment fingerFragment = new FingerFragment();
```


### 使用前初始化接入参数


```java
    fingerFragment.show(getFragmentManager(),"fingerFragment");
                fingerFragment.setmFragmentCallBack(new FingerFragment.Callback() {
                    @Override
                    public void onSuccess() {
                       //识别成功 Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError() {
                       //识别失败 Toast.makeText(MainActivity.this, "弹密码框", Toast.LENGTH_SHORT).show();
                    }
                });
```


 
<a name="混淆文件"></a>
## 混淆文件


```java
 
 
# MeiZuFingerprint
-keep class com.fingerprints.service.** { *; }

# SmsungFingerprint
-keep class com.samsung.android.sdk.** { *; }
```

<a name="i-want-to-know-more"></a>
# I want to know more!
支持原创
项目参考地址：http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2017/0213/7109.html

更多功能接口，详见资源包内demo

<a name="支持"></a>
# 支持

```
群名称：Android技术交流群
群   号：189532981
```
