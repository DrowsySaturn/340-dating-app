package client.controllers.logincontroller;
/**
 * This class utility ServerCommunication to validate the user's login.
 * @Author:VincentYang
 * @Date:12/3/2018
 */

import com.datingapp.client.net.DatingNetworkException;
import com.datingapp.client.net.ServerCommunicator;
import com.datingapp.shared.datapersistence.LoginConfirmation;

public class LoginServerCommunicator {
    /**
     * This method uses the ServerCommunicator to validate the user's login.
     * @param _email
     * @param _password
     * @return
     */
    public static LoginConfirmation validateLogin(String _email, String _password) {
        LoginConfirmation loginConfirmation = null;
        try{
            loginConfirmation = ServerCommunicator.validateLogin(_email, _password);
        } catch (DatingNetworkException ex) {
            ex.printStackTrace();
        } finally {
            return loginConfirmation;
        }
    }
}
