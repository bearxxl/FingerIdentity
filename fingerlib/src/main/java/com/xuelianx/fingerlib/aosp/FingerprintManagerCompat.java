

package com.xuelianx.fingerlib.aosp;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.os.CancellationSignal;

import  com.xuelianx.fingerlib.aosp.FingerprintManagerCompatApi23;

import java.security.Signature;

import javax.crypto.Cipher;
import javax.crypto.Mac;

public final class FingerprintManagerCompat {

    private Context mContext;

    public static  com.xuelianx.fingerlib.aosp.FingerprintManagerCompat from(Context context) {
        return new  com.xuelianx.fingerlib.aosp.FingerprintManagerCompat(context);
    }

    private FingerprintManagerCompat(Context context) {
        mContext = context;
    }

    private static final FingerprintManagerCompatImpl IMPL;

    static {
        IMPL = new Api23FingerprintManagerCompatImpl();
    }

    public boolean hasEnrolledFingerprints() {
        return IMPL.hasEnrolledFingerprints(mContext);
    }

    public boolean isHardwareDetected() {
        return IMPL.isHardwareDetected(mContext);
    }

    public void authenticate(@Nullable CryptoObject crypto, int flags,
                             @Nullable CancellationSignal cancel, @NonNull AuthenticationCallback callback,
                             @Nullable Handler handler) {
        IMPL.authenticate(mContext, crypto, flags, cancel, callback, handler);
    }

    public static class CryptoObject {

        private final Signature mSignature;
        private final Cipher mCipher;
        private final Mac mMac;

        public CryptoObject(Signature signature) {
            mSignature = signature;
            mCipher = null;
            mMac = null;

        }

        public CryptoObject(Cipher cipher) {
            mCipher = cipher;
            mSignature = null;
            mMac = null;
        }

        public CryptoObject(Mac mac) {
            mMac = mac;
            mCipher = null;
            mSignature = null;
        }

        public Signature getSignature() {
            return mSignature;
        }

        public Cipher getCipher() {
            return mCipher;
        }

        public Mac getMac() {
            return mMac;
        }
    }

    public static final class AuthenticationResult {
        private CryptoObject mCryptoObject;

        public AuthenticationResult(CryptoObject crypto) {
            mCryptoObject = crypto;
        }

        public CryptoObject getCryptoObject() {
            return mCryptoObject;
        }
    }

    public static abstract class AuthenticationCallback {

        public void onAuthenticationError(int errMsgId, CharSequence errString) {
        }

        public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        }

        public void onAuthenticationSucceeded(AuthenticationResult result) {
        }

        public void onAuthenticationFailed() {
        }
    }

    private interface FingerprintManagerCompatImpl {
        boolean hasEnrolledFingerprints(Context context);

        boolean isHardwareDetected(Context context);

        void authenticate(Context context, CryptoObject crypto, int flags, CancellationSignal cancel, AuthenticationCallback callback, Handler handler);
    }

    private static class Api23FingerprintManagerCompatImpl implements FingerprintManagerCompatImpl {

        public Api23FingerprintManagerCompatImpl() {
        }

        @Override
        public boolean hasEnrolledFingerprints(Context context) {
            return  com.xuelianx.fingerlib.aosp.FingerprintManagerCompatApi23.hasEnrolledFingerprints(context);
        }

        @Override
        public boolean isHardwareDetected(Context context) {
            return  com.xuelianx.fingerlib.aosp.FingerprintManagerCompatApi23.isHardwareDetected(context);
        }

        @Override
        public void authenticate(Context context, CryptoObject crypto, int flags, CancellationSignal cancel,
                                 AuthenticationCallback callback, Handler handler) {
             com.xuelianx.fingerlib.aosp.FingerprintManagerCompatApi23.authenticate(context, wrapCryptoObject(crypto), flags,
                    cancel != null ? cancel.getCancellationSignalObject() : null, wrapCallback(callback), handler);
        }

        private static  com.xuelianx.fingerlib.aosp.FingerprintManagerCompatApi23.CryptoObject wrapCryptoObject(CryptoObject cryptoObject) {
            if (cryptoObject == null) {
                return null;
            } else if (cryptoObject.getCipher() != null) {
                return new  com.xuelianx.fingerlib.aosp.FingerprintManagerCompatApi23.CryptoObject(cryptoObject.getCipher());
            } else if (cryptoObject.getSignature() != null) {
                return new  com.xuelianx.fingerlib.aosp.FingerprintManagerCompatApi23.CryptoObject(cryptoObject.getSignature());
            } else if (cryptoObject.getMac() != null) {
                return new  com.xuelianx.fingerlib.aosp.FingerprintManagerCompatApi23.CryptoObject(cryptoObject.getMac());
            } else {
                return null;
            }
        }

        static CryptoObject unwrapCryptoObject( com.xuelianx.fingerlib.aosp.FingerprintManagerCompatApi23.CryptoObject cryptoObject) {
            if (cryptoObject == null) {
                return null;
            } else if (cryptoObject.getCipher() != null) {
                return new CryptoObject(cryptoObject.getCipher());
            } else if (cryptoObject.getSignature() != null) {
                return new CryptoObject(cryptoObject.getSignature());
            } else if (cryptoObject.getMac() != null) {
                return new CryptoObject(cryptoObject.getMac());
            } else {
                return null;
            }
        }

        private static  com.xuelianx.fingerlib.aosp.FingerprintManagerCompatApi23.AuthenticationCallback wrapCallback(final AuthenticationCallback callback) {
            return new  com.xuelianx.fingerlib.aosp.FingerprintManagerCompatApi23.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errMsgId, CharSequence errString) {
                    callback.onAuthenticationError(errMsgId, errString);
                }

                @Override
                public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                    callback.onAuthenticationHelp(helpMsgId, helpString);
                }

                @Override
                public void onAuthenticationSucceeded(FingerprintManagerCompatApi23.AuthenticationResultInternal result) {
                    callback.onAuthenticationSucceeded(new AuthenticationResult(unwrapCryptoObject(result.getCryptoObject())));
                }

                @Override
                public void onAuthenticationFailed() {
                    callback.onAuthenticationFailed();
                }
            };
        }
    }
}
