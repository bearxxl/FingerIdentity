package com.xuelianx.fingerlib.impl;

import android.content.Context;

import com.samsung.android.sdk.pass.Spass;
import com.samsung.android.sdk.pass.SpassFingerprint;
import com.samsung.android.sdk.pass.SpassInvalidStateException;
import com.xuelianx.fingerlib.base.BaseFingerprint;


public class SamsungFingerprint extends BaseFingerprint {

    private int mResultCode = -1;
    private SpassFingerprint mSpassFingerprint;

    public SamsungFingerprint(Context context, FingerprintIdentifyExceptionListener exceptionListener) {
        super(context, exceptionListener);

        try {
            Spass spass = new Spass();
            spass.initialize(mContext);
            mSpassFingerprint = new SpassFingerprint(mContext);
            setHardwareEnable(spass.isFeatureEnabled(Spass.DEVICE_FINGERPRINT));
            setRegisteredFingerprint(mSpassFingerprint.hasRegisteredFinger());
        } catch (Throwable e) {
            onCatchException(e);
        }
    }

    @Override
    protected void doIdentify() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    mSpassFingerprint.startIdentify(new SpassFingerprint.IdentifyListener() {
                        @Override
                        public void onFinished(int i) {
                            mResultCode = i;
                        }

                        @Override
                        public void onReady() {

                        }

                        @Override
                        public void onStarted() {

                        }

                        @Override
                        public void onCompleted() {
                            switch (mResultCode) {
                                case SpassFingerprint.STATUS_AUTHENTIFICATION_SUCCESS:
                                case SpassFingerprint.STATUS_AUTHENTIFICATION_PASSWORD_SUCCESS:
                                    onSucceed();
                                    break;

                                case SpassFingerprint.STATUS_SENSOR_FAILED:
                                case SpassFingerprint.STATUS_OPERATION_DENIED:
                                case SpassFingerprint.STATUS_TIMEOUT_FAILED:
                                case SpassFingerprint.STATUS_BUTTON_PRESSED:
                                case SpassFingerprint.STATUS_QUALITY_FAILED:
                                case SpassFingerprint.STATUS_USER_CANCELLED_BY_TOUCH_OUTSIDE:
                                case SpassFingerprint.STATUS_AUTHENTIFICATION_FAILED:
                                    onNotMatch();
                                    break;

                                case SpassFingerprint.STATUS_USER_CANCELLED:
                                    // do nothing
                                    break;

                                default:
                                    onFailed(false);
                                    break;
                            }
                        }
                    });
                } catch (Throwable e) {
                    if (e instanceof SpassInvalidStateException) {
                        SpassInvalidStateException stateException = (SpassInvalidStateException) e;
                        if (stateException.getType() == 1) {
                            onFailed(true);
                        } else {
                            onCatchException(e);
                            onFailed(false);
                        }
                    } else {
                        onCatchException(e);
                        onFailed(false);
                    }
                }
            }
        });
    }

    @Override
    protected void doCancelIdentify() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (mSpassFingerprint != null) {
                        mSpassFingerprint.cancelIdentify();
                    }
                } catch (Throwable e) {
                    onCatchException(e);
                }
            }
        });
    }
}
