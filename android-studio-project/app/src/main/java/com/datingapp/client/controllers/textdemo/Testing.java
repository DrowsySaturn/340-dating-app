package com.datingapp.client.controllers.textdemo;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.datingapp.client.cachelibrary.ProfileCache;
import com.datingapp.client.controllers.profilecontroller.ProfileController;
import com.datingapp.client.controllers.signupcontroller.SignUpController;
import com.datingapp.shared.dataobjects.Profile;

public class Testing {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void main(String[] args) {
//        String email = "vinny@cum.com";
//        String password = "123456789";
//        SignUpController.signUp(email,password);
        String firstName = "Vinny";
        String lastName = "Yang";
        int age = 24;
        String personalMessage = "LOLXD";
//        ProfileController.createProfile(age,firstName,lastName,personalMessage);
    }
}
