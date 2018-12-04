package eventsinterfaces.events;
/**
 * This class is an instance of a login event.
 * @Author:VincentYang
 * @Date:12/3/2018
 */

import com.datingapp.shared.datapersistence.LoginConfirmation;

public class LoginEvent implements Event<Boolean> {
    //This is the storage of an event. This contains loginConfirmation.
    private LoginConfirmation loginConfirmation;

    /**
     * This is the contructor the for the event.
     * @param _loginConformation
     */
    public LoginEvent(LoginConfirmation _loginConformation) {
        this.loginConfirmation = _loginConformation;
    }

    /**
     * Over ride the interface action, to return a boolean, if the user has logged in.
     * @return boolean.
     */
    @Override
    public Boolean fireEvent() {
        //TODO: List the profile view.
        System.out.println("User has logged in!");
        return new Boolean(loginConfirmation.isSuccess());
    }

    /**
     * This is a getter for loginConfirmation.
     * @return this.loginConfirmation.
     */
    public LoginConfirmation getLoginConfirmation() {
        return this.loginConfirmation;
    }
}
