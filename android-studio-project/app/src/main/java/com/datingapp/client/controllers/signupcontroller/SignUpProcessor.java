package com.datingapp.client.controllers.signupcontroller;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.datingapp.client.controllers.AuthenticationInterface;

import java.security.NoSuchAlgorithmException;

public class SignUpProcessor {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean checkForDuplicateEmail(String _email) throws NoSuchAlgorithmException {
        return AuthenticationInterface.isDuplicateLoginID(_email);
    }
}
