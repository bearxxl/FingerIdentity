package com.xuelianx.fingerlib.base;

/**
 * Title: FingerSupportExceptionListener.java
 * Description:
 * Copyright (c) 个人版权所有 2019/8/15
 * Created DateTime: 2019/8/15 10:17
 * Created by xuelianXiong.
 */
public interface FingerSupportExceptionListener {
    void hardwareDisable();//硬件不支持

    void registeredNone();//没注册过指纹

}
