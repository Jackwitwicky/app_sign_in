package com.jacknkiarie.signinui.models;

import android.content.Context;
import android.content.Intent;

import com.jacknkiarie.signinui.EmailPasswordActivity;
import com.jacknkiarie.signinui.PinActivity;

public class SignInUI {
    // constants
    public static final String EMAIL_PASSWORD = "EMAIL_PASSWORD";
    public static final String PIN = "PIN";
    public static final String FINGEPRINT = "FINGERPRINT";

    private String signInType;

    private SignInUI(final Builder builder) {
        signInType = builder.signInType;

        if (signInType.equals(EMAIL_PASSWORD)) {
            Intent emailIntent = new Intent(builder.context, EmailPasswordActivity.class);
            builder.context.startActivity(emailIntent);
        }
        else if(signInType.equals(PIN)) {
            Intent pinIntent = new Intent(builder.context, PinActivity.class);
            builder.context.startActivity(pinIntent);
        }
    }

    static class Builder {
        private Context context;
        private String signInType;
        private int passwordLength;
        private int pinLength;

        public Builder(Context context) {
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

        public SignInUI create() {
            return new SignInUI(this);
        }
    }
}
