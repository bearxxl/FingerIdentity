package com.xuelianx.fingerlib;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xuelianx.fingerlib.base.ExceptionListener;
import com.xuelianx.fingerlib.base.FingerIdentifyListener;
import com.xuelianx.fingerlib.base.FingerSupportExceptionListener;


/**
 * Title: FingerFragment.java
 * Description:
 * Copyright (c) 个人版权所有 2019/8/15
 * Created DateTime: 2019/8/15 10:50
 * Created by xuelianXiong.
 */
public class FingerFragment extends DialogFragment {

    Dialog mDialog;
    LinearLayout ll_btn;
    TextView tv_msg;
    ImageView iv;

    private Callback mCallback;

    private FingerprintIdentify mFingerprintIdentify;

    private static final int MAX_AVAILABLE_TIMES = 3;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (mDialog == null) {
            mDialog = new Dialog(getActivity(), R.style.petgirls_dialog);
            mDialog.setContentView(R.layout.fragment_finger);
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.getWindow().setGravity(Gravity.CENTER);
            View view = mDialog.getWindow().getDecorView();
            tv_msg = (TextView) view.findViewById(R.id.tv_msg);
            ll_btn = (LinearLayout) view.findViewById(R.id.ll_btn);
            iv = (ImageView) view.findViewById(R.id.iv);
            view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });

            view.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCallback != null) {
                        mCallback.onError();
                        dismiss();
                    }
                }
            });
            mFingerprintIdentify = new FingerprintIdentify(getActivity());
            mFingerprintIdentify.setSupportAndroidL(false);

            mFingerprintIdentify.setExceptionListener(new ExceptionListener() {
                @Override
                public void onCatchException(Throwable exception) {
//                    Toast.makeText(getActivity(), exception.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            mFingerprintIdentify.setIsSupportMeiZu(true);
            mFingerprintIdentify.setIsSupportSamsung(true);
            mFingerprintIdentify.init();
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
                    start();
                }

            });

        }

        return mDialog;
    }


    public void setmFragmentCallBack(Callback mFragmentCallBack) {
        this.mCallback = mFragmentCallBack;
    }

    @Override
    public void onResume() {
        super.onResume();
//        mFingerprintIdentify.resumeIdentify();
    }

    public void start() {
        mFingerprintIdentify.startIdentify(MAX_AVAILABLE_TIMES, new FingerIdentifyListener() {
            @Override
            public void onSucceed() {
                if (mCallback != null) {
                    mCallback.onSuccess();
                }
                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
                }
            }

            @Override
            public void onNotMatch(int availableTimes) {
                tv_msg.setTextColor(getResources().getColor(R.color.color_FB544B));
                if (availableTimes == 0) {
                    tv_msg.setText("指纹验证失败，转密码支付");
                } else {
                    tv_msg.setText(R.string.verify_failed);
                }
                shake(iv);
                shake(tv_msg);
            }

            @Override
            public void onFailed(boolean isDeviceLocked) {
                tv_msg.setText("指纹验证失败，转密码支付");
                tv_msg.setTextColor(getResources().getColor(R.color.color_FB544B));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mDialog != null && mDialog.isShowing()) {
                            mDialog.dismiss();
                            mCallback.onError();
                        }
                    }
                }, 1000);
            }

            @Override
            public void onStartFailedByDeviceLocked() {
                tv_msg.setText("指纹验证太过频繁，请稍后重试,转密码支付");
                tv_msg.setTextColor(getResources().getColor(R.color.color_FB544B));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mDialog != null && mDialog.isShowing()) {
                            mDialog.dismiss();
                            mCallback.onError();
                        }
                    }
                }, 5000);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        mFingerprintIdentify.cancelIdentify();
    }

    private void shake(View v) {
        Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        v.startAnimation(shake);
    }


    public interface Callback {

        void onSuccess();

        void onError();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFingerprintIdentify.cancelIdentify();
        mCallback = null;
    }
}
