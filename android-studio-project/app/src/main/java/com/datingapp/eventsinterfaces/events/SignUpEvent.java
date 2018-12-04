package com.datingapp.eventsinterfaces.events;
/**
 * This class is an instance of SignUp event.
 * @Author:VincentYang
 * @Date:12/3/2018
 */

import com.datingapp.shared.dataobjects.LoginInformation;


public class SignUpEvent implements Event<LoginInformation> {
    //This stores the login information from the user.
    private LoginInformation loginInformation;


    /**
     * This is the contructor for sign up event.
     * @param _loginInformation
     */
    public SignUpEvent(LoginInformation _loginInformation) {
        this.loginInformation = _loginInformation;
    }


    /**
     * This over rides the interface method, it returns the loginInformation.
      * @return this.loginInformation.
     */
    @Override
    public LoginInformation fireEvent() {
        return this.loginInformation;
    }
}
