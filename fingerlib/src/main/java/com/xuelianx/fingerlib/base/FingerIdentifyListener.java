package com.xuelianx.fingerlib.base;

/**
 * Title: FingerIdentifyListener.java
 * Description:
 * Copyright (c) 个人版权所有 2019/8/14
 * Created DateTime: 2019/8/14 14:10
 * Created by xuelianXiong.
 */
public interface FingerIdentifyListener {

    void onSucceed();//指纹识别成功

    void onNotMatch(int availableTimes);//不匹配，及可用次数

    void onFailed(boolean isDeviceLocked);//失败，设备是否已锁屏

    void onStartFailedByDeviceLocked();
}
