package com.datingapp.client.controllers.profilecontroller;
/**
 * This class is a rapper class to call DeletProfileProcessor, and it will clear up all the cached values.
 * @Author:VincentYang
 * @Date:12/3/2018
 */

import com.datingapp.client.cachelibrary.LoginInformationCache;
import com.datingapp.client.cachelibrary.MatchesCache;
import com.datingapp.client.cachelibrary.ProfileCache;
import com.datingapp.client.controllers.actionprocessors.DeleteProfileProcessor;

public class DeleteProfileActuator {


    /**
     * This method will invoke the DeleteProfileProcessor to in active the current profile. During the process it will clear up all the cached values.
     */
    public static void deleteProfile() {
        ProfileCache.getInstance().clear();
        LoginInformationCache.getInstance().clear();
        MatchesCache.getInstance().clear();
        DeleteProfileProcessor.process();
    }
}
