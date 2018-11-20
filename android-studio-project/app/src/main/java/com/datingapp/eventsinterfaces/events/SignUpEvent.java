package com.datingapp.eventsinterfaces.events;

import com.datingapp.client.net.DatingNetworkException;
import com.datingapp.client.net.ServerCommunicator;
import com.datingapp.shared.dataobjects.LoginInformation;
import com.datingapp.shared.datapersistence.LoginConfirmation;

public class SignUpEvent implements Event {

    private LoginInformation loginInformation;

    public SignUpEvent(LoginInformation _loginInformation) {
        this.loginInformation = _loginInformation;
    }

    @Override
    public void fireEvent() {
        try {
            ServerCommunicator.registerProfile(this.loginInformation);
        } catch (DatingNetworkException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "Sign Up Event";
    }
}
