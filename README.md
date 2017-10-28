<p align="center">
<font size="30" color="#123456">ָ��ʶ��</font>
</p>

# Latest Version
##### _OCT 23, 2017_ - [v1.0.0](#1.0.0)
֧��Androidԭ��6.0������+����+����


<!-- MarkdownTOC -->
<img src="screenshots/screenshots1.png" height="400" alt="Screenshot"/> <img src="screenshots/screenshots2.png" height="400" alt="Screenshot"/> <img src="screenshots/screenshots3.png" height="400" alt="Screenshot"/>
- [Quick Start Guide](#quick-start-guide)
    - [׼������](#׼������)
    - [�嵥�ļ�](#�嵥�ļ�)
    - [���ò���](#���ò���)
    - [�����ļ�](#�����ļ�)
- [I want to know more!](#i-want-to-know-more)
- [֧��](#֧��)

<!-- /MarkdownTOC -->

<a name="quick-start-guide"></a>
# Quick Start Guide


<a name="׼������"></a>
## ׼������

### ����������

 1.app/build.gradle���������
 
     compile 'com.xuelianx.finger:fingerlib:1.0.0'




<a name="���ò���"></a>
## ���ò���

### Initialization

 
```java
//��ʼ��
  FingerFragment fingerFragment = new FingerFragment();
```


### ʹ��ǰ��ʼ���������


```java
    fingerFragment.show(getFragmentManager(),"fingerFragment");
                fingerFragment.setmFragmentCallBack(new FingerFragment.Callback() {
                    @Override
                    public void onSuccess() {
                       //ʶ��ɹ� Toast.makeText(MainActivity.this, "�ɹ�", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError() {
                       //ʶ��ʧ�� Toast.makeText(MainActivity.this, "�������", Toast.LENGTH_SHORT).show();
                    }
                });
```


 
<a name="�����ļ�"></a>
## �����ļ�


```java
 
 
# MeiZuFingerprint
-keep class com.fingerprints.service.** { *; }

# SmsungFingerprint
-keep class com.samsung.android.sdk.** { *; }
```

<a name="i-want-to-know-more"></a>
# I want to know more!
֧��ԭ��
��Ŀ�ο���ַ��http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2017/0213/7109.html
ע�⣺�������ʹ��25�汾�ļ��ݿ⣬���ܻᵼ�²��ּ�ʹ��6.0ϵͳ�Ļ���Ҳ��������ʹ��ָ��ʶ��

���๦�ܽӿڣ������Դ����demo

<a name="֧��"></a>
# ֧��

```
Ⱥ���ƣ�Android��������Ⱥ
Ⱥ   �ţ�189532981
```
