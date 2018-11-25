package com.datingapp.client.controllers.profilecontroller;

import com.datingapp.shared.dataobjects.Profile;

public class ProfileFactory {
    private static Profile profile = null;


    public static Profile createProfile(int _age, String _name, String _personalMessage) {
        ProfileFactory.profile = new Profile(_age, _name, _personalMessage);
        return ProfileFactory.profile;
    }

<<<<<<< HEAD:android-studio-project/app/src/main/java/com/datingapp/client/controllers/profilecontroller/ProfileProcessor.java
    public static Profile loadProfile(int _age, String _name, String _personalMessage) {
        ProfileProcessor.profile =
    }
=======


>>>>>>> vincent:android-studio-project/app/src/main/java/com/datingapp/client/controllers/profilecontroller/ProfileFactory.java
}
