<p align="center">
<font size="30" color="#123456">指纹识别</font>
</p>

# Latest Version
##### _OCT 23, 2017_ - [v1.0.0](#1.0.0)
支持Android原生6.0及以上+魅族+三星


<!-- MarkdownTOC -->
<img src="https://github.com/bearxxl/FingerIdentity/master/screenshoots/screenshoots1.png" height="400" alt="Screenshot"/>
<img src="https://github.com/bearxxl/FingerIdentity/master/screenshoots/screenshoots1.png" height="400" alt="Screenshot"/>
<img src="https://github.com/bearxxl/FingerIdentity/master/screenshoots/screenshoots1.png" height="400" alt="Screenshot"/>

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
## 调用：分两种方式

### 方法一：使用FingerFragment的样式和逻辑
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

### 方法二：使用FingerprintIdentify自定义样式和逻辑
### Initialization

```java
 FingerprintIdentify mFingerprintIdentify = new FingerprintIdentify(Context context);
 ```

 ### 使用前初始化接入参数

 ```java
  mFingerprintIdentify.setSupportAndroidL(true);//是否兼容5.0
  mFingerprintIdentify.setIsSupportMeiZu(true);//是否适配魅族
  mFingerprintIdentify.setIsSupportSamsung(true);//是否适配三星
   mFingerprintIdentify.setExceptionListener(new ExceptionListener() {
                @Override
                public void onCatchException(Throwable exception) {
                    Toast.makeText(getActivity(), exception.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });

    mFingerprintIdentify.init();//初始化
    //检测硬件是否支持，需在初始化之后判断
     mFingerprintIdentify.checkSupport(new FingerSupportExceptionListener() {
                    @Override
                    public void hardwareDisable() {
                        Toast.makeText(getActivity(), "硬件不支持", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }

                    @Override
                    public void registeredNone() {
                        Toast.makeText(getActivity(), "请先进入手机--设置，录入至少一个指纹", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }

                     @Override
                     public void isEnable() {
                        //指纹可用，开始识别
                     }

                });

    //开始识别
      mFingerprintIdentify.startIdentify(MAX_AVAILABLE_TIMES, new FingerIdentifyListener() {
            @Override
            public void onSucceed() {

            }

            @Override
            public void onNotMatch(int availableTimes) {

            }

            @Override
            public void onFailed(boolean isDeviceLocked) {

            }

            @Override
            public void onStartFailedByDeviceLocked() {

            }
        });
    }
  、、、


 
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
