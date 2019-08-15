package com.xuelianx.fingerlib;

import android.content.Context;

import com.xuelianx.fingerlib.base.BaseFingerprint;
import com.xuelianx.fingerlib.base.ExceptionListener;
import com.xuelianx.fingerlib.base.FingerIdentifyListener;
import com.xuelianx.fingerlib.base.FingerSupportExceptionListener;
import com.xuelianx.fingerlib.impl.AndroidFingerprint;
import com.xuelianx.fingerlib.impl.MeiZuFingerprint;
import com.xuelianx.fingerlib.impl.SamsungFingerprint;

/**
 * Copyright (c) 2017 Awei
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * <p>
 * Created by Awei on 2017/2/8.
 */
public class FingerprintIdentify {

    protected Context mContext;
    protected ExceptionListener mExceptionListener;
    protected FingerSupportExceptionListener mFingerSupportExceptionListener;

    protected boolean mIsSupportAndroidL = false;//是否支持5.0
    protected boolean mIsSupportSamsung = false;//是否支持三星
    protected boolean mIsSupportMeiZu = false;//是否支持魅族

    protected BaseFingerprint mFingerprint;
    protected BaseFingerprint mSubFingerprint;

    public FingerprintIdentify(Context context) {
        mContext = context;
    }

    public void setSupportAndroidL(boolean supportAndroidL) {
        mIsSupportAndroidL = supportAndroidL;
    }

    public void setExceptionListener(ExceptionListener exceptionListener) {
        mExceptionListener = exceptionListener;
    }

    public void setFingerSupportExceptionListener(FingerSupportExceptionListener fingerSupportExceptionListener) {
        mFingerSupportExceptionListener = fingerSupportExceptionListener;
    }

    public void init() {

        AndroidFingerprint androidFingerprint = new AndroidFingerprint(mContext, mExceptionListener, mIsSupportAndroidL);
        if (androidFingerprint.isHardwareEnable()) {
            mSubFingerprint = androidFingerprint;
            if (androidFingerprint.isRegisteredFingerprint()) {
                mFingerprint = androidFingerprint;
                return;
            }
        }

        if (mIsSupportSamsung) {
            SamsungFingerprint samsungFingerprint = new SamsungFingerprint(mContext, mExceptionListener);
            if (samsungFingerprint.isHardwareEnable()) {
                mSubFingerprint = samsungFingerprint;
                if (samsungFingerprint.isRegisteredFingerprint()) {
                    mFingerprint = samsungFingerprint;
                    return;
                }
            }
        }

        if (mIsSupportMeiZu) {
            MeiZuFingerprint meiZuFingerprint = new MeiZuFingerprint(mContext, mExceptionListener);
            if (meiZuFingerprint.isHardwareEnable()) {
                mSubFingerprint = meiZuFingerprint;
                if (meiZuFingerprint.isRegisteredFingerprint()) {
                    mFingerprint = meiZuFingerprint;
                }
            }
        }

        if (null != mFingerSupportExceptionListener) {
            if (!isHardwareEnable()) {
                mFingerSupportExceptionListener.hardwareDisable();
                return;
            }
            if (!isRegisteredFingerprint()) {
                mFingerSupportExceptionListener.registeredNone();
                return;
            }
        }

    }

    // DO
    public void startIdentify(int maxAvailableTimes, FingerIdentifyListener listener) {
        if (!isFingerprintEnable()) {
            return;
        }

        mFingerprint.startIdentify(maxAvailableTimes, listener);
    }

    public void cancelIdentify() {
        if (mFingerprint != null) {
            mFingerprint.cancelIdentify();
        }
    }

    public void resumeIdentify() {
        if (!isFingerprintEnable()) {
            return;
        }

        mFingerprint.resumeIdentify();
    }

    // GET & SET
    public boolean isFingerprintEnable() {
        return mFingerprint != null && mFingerprint.isEnable();
    }

    public boolean isHardwareEnable() {
        return isFingerprintEnable() || (mSubFingerprint != null && mSubFingerprint.isHardwareEnable());
    }

    public boolean isRegisteredFingerprint() {
        return isFingerprintEnable() || (mSubFingerprint != null && mSubFingerprint.isRegisteredFingerprint());
    }


    public void setIsSupportSamsung(boolean mIsSupportSamsung) {
        this.mIsSupportSamsung = mIsSupportSamsung;
    }

    public void setIsSupportMeiZu(boolean mIsSupportMeiZu) {
        this.mIsSupportMeiZu = mIsSupportMeiZu;
    }
}
