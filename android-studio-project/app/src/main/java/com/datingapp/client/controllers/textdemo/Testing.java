package com.datingapp.client.controllers.textdemo;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.datingapp.client.controllers.signupcontroller.SignUpController;

public class Testing {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void main(String[] args) {
        String email = "vinny@cum.com";
        String password = "123456789";
        SignUpController.signUp(email,password);
    }
}
