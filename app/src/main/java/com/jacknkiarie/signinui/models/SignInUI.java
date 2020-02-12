package com.jacknkiarie.signinui.models;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.jacknkiarie.signinui.EmailPasswordActivity;
import com.jacknkiarie.signinui.FingerprintActivity;
import com.jacknkiarie.signinui.PinActivity;

public class SignInUI {
    // constants
    public static final String EMAIL_PASSWORD_FORM = "EMAIL_PASSWORD_FORM";
    public static final String PIN_FORM = "PIN_FORM";
    public static final String FINGERPRINT_FORM = "FINGERPRINT_FORM";
    public static final int REQUEST_CODE = 100;
    public static final int RESULT_OK = 69;
    public static final int RESULT_CANCEL = 77;

    public static final int DEFAULT_PASSWORD_LENGTH = 4;
    public static final int DEFAULT_PIN_LENGTH = 4;

    // variables used when passing data to various activities via intents
    public static final String EXTRA_PASSWORD_LENGTH = "EXTRA_PASSWORD_LENGTH";
    public static final String EXTRA_PIN_LENGTH = "EXTRA_PIN_LENGTH";
    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_SUBTITLE = "EXTRA_SUBTITLE";
    public static final String EXTRA_IS_PIN_ENABLED = "EXTRA_IS_PIN_ENABLED";
    public static final String EXTRA_IS_EMAIL_ENABLED = "EXTRA_IS_EMAIL_ENABLED";
    public static final String EXTRA_IS_FINGEPRINT_ENABLED = "EXTRA_IS_FINGEPRINT_ENABLED";
    public static final String EXTRA_IS_PIN_HIDDEN = "EXTRA_IS_PIN_HIDDEN";

    public static final String PARAM_SIGN_IN_TYPE = "PARAM_SIGN_IN_TYPE";
    public static final String PARAM_RESULT_STATUS = "PARAM_RESULT_STATUS";
    public static final String PARAM_EMAIL = "PARAM_EMAIL";
    public static final String PARAM_PASSWORD = "PARAM_PASSWORD";
    public static final String PARAM_PIN = "PARAM_PIN";

    private String signInType;

    private SignInUI(final Builder builder) {
        signInType = builder.signInType;

        if (signInType == null || signInType.isEmpty()) {
            throw new IllegalStateException("The Sign In Type Parameter must be provided");
        }

        if (signInType.equals(EMAIL_PASSWORD_FORM)) {
            Intent emailIntent = new Intent(builder.context, EmailPasswordActivity.class);
            emailIntent.putExtra(EXTRA_TITLE, builder.title);
            emailIntent.putExtra(EXTRA_SUBTITLE, builder.subtitle);
            emailIntent.putExtra(EXTRA_PASSWORD_LENGTH, builder.passwordLength);
            emailIntent.putExtra(EXTRA_IS_PIN_ENABLED, builder.isPinSignInEnabled);
            emailIntent.putExtra(EXTRA_IS_FINGEPRINT_ENABLED, builder.isFingerprintSignInEnabled);
            builder.context.startActivityForResult(emailIntent, REQUEST_CODE);
        }
        else if(signInType.equals(PIN_FORM)) {
            Intent pinIntent = new Intent(builder.context, PinActivity.class);
            pinIntent.putExtra(EXTRA_TITLE, builder.title);
            pinIntent.putExtra(EXTRA_SUBTITLE, builder.subtitle);
            pinIntent.putExtra(EXTRA_PIN_LENGTH, builder.pinLength);
            pinIntent.putExtra(EXTRA_IS_PIN_HIDDEN, builder.isPinHidden);
            pinIntent.putExtra(EXTRA_IS_EMAIL_ENABLED, builder.isEmailSignInEnabled);
            pinIntent.putExtra(EXTRA_IS_FINGEPRINT_ENABLED, builder.isFingerprintSignInEnabled);
            builder.context.startActivityForResult(pinIntent, REQUEST_CODE);
        }
        else if(signInType.equals(FINGERPRINT_FORM)) {
            Intent fingerprintIntent = new Intent(builder.context, FingerprintActivity.class);
            fingerprintIntent.putExtra(EXTRA_TITLE, builder.title);
            fingerprintIntent.putExtra(EXTRA_SUBTITLE, builder.subtitle);
            fingerprintIntent.putExtra(EXTRA_IS_EMAIL_ENABLED, builder.isEmailSignInEnabled);
            fingerprintIntent.putExtra(EXTRA_IS_PIN_ENABLED, builder.isPinSignInEnabled);
            builder.context.startActivityForResult(fingerprintIntent, REQUEST_CODE);
        }
    }

    public static class Builder {
        private AppCompatActivity context;
        private String signInType;
        private String title = "";
        private String subtitle = "";
        private int passwordLength = DEFAULT_PASSWORD_LENGTH;
        private int pinLength = DEFAULT_PIN_LENGTH;
        private boolean isEmailSignInEnabled = false;
        private boolean isPinSignInEnabled = false;
        private boolean isFingerprintSignInEnabled = false;
        private boolean isPinHidden = true;

        public Builder(AppCompatActivity context) {
            this.context = context;
        }

        public Builder setSignInType(final String signInType) {
            this.signInType = signInType;
            return this;
        }

        public Builder setPasswordLength(final int passwordLength) {
            this.passwordLength = passwordLength;
            return this;
        }

        public Builder setPinLength(final int pinLength) {
            this.pinLength = pinLength;
            return this;
        }

        public Builder setTitle(final String title) {
            this.title = title;
            return this;
        }

        public Builder setSubtitle(final String subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        public Builder setEmailSignInEnabled(boolean emailSignInEnabled) {
            this.isEmailSignInEnabled = emailSignInEnabled;
            return this;
        }

        public Builder setPinSignInEnabled(boolean pinSignInEnabled) {
            this.isPinSignInEnabled = pinSignInEnabled;
            return this;
        }

        public Builder setFingerprintSignInEnabled(boolean fingerprintSignInEnabled) {
            this.isFingerprintSignInEnabled = fingerprintSignInEnabled;
            return this;
        }

        public Builder setPinAsHidden(boolean isPinHidden) {
            this.isPinHidden = isPinHidden;
            return this;
        }

        public SignInUI build() {
            return new SignInUI(this);
        }
    }
}
