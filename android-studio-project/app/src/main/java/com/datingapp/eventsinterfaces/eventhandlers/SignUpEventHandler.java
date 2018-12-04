package eventsinterfaces.eventhandlers;
/**
 * This is an instance of SignUp event handler, it stores in the SignUp events.
 * @Author:VincentYang
 * @Date:12/3/2018
 */

import com.datingapp.eventsinterfaces.events.Event;
import com.datingapp.eventsinterfaces.events.SignUpEvent;
import com.datingapp.shared.dataobjects.LoginInformation;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SignUpEventHandler implements EventHandler {

    /**
     * This is a singleton design. This will return an instance of SignUpEventHandler.
     */
    ///////////////////////////////////////////////////////////////////////
    private static SignUpEventHandler instance = null;


    public static SignUpEventHandler getInstance() {
        if(instance == null) {
            return SignUpEventHandler.instance = new SignUpEventHandler();
        } else {
            return SignUpEventHandler.instance;
        }
    }
    //////////////////////////////////////////////////////////////////////


    /**
     * This is an event queue, it holds the event object of SignUpEvent.
     */
    private Queue<Event> events = new LinkedList<>();


    /**
     *This method checks if the passed in event is an instance of SignUpEvent. Then stores the event into the queue.
     * @param _event
     */
    @Override
    public void addEvent(Event _event) {
        if(_event instanceof SignUpEvent) {
            this.events.add(_event);
        } else {
            try {
                throw new Exception("This is not an instance of SignUpEvent");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * This method will empty the queue, and perform fireEvent action on every single event objects.
     * @return ArrayList.
     */
    @Override
    public ArrayList<LoginInformation> fireAllEvents() {
        ArrayList<LoginInformation> eventArrayList = new ArrayList<>();
        if(this.events.isEmpty()) {
            try {
                throw new Exception("The SignUp events queue is empty");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            while(!events.isEmpty()) {
                Event event = this.events.remove();
                eventArrayList.add((LoginInformation)event.fireEvent());
            }
        }
        return eventArrayList;
    }


    /**
     * This method will force fire a selected event. DO NOT USE, IF NOT NECESSARY.
     * @param _event
     * @return LoginInformation
     */
    @Override
    public LoginInformation fireEvent(Event _event) {
        if(this.events.isEmpty()) {
            try {
                throw new Exception("The SignUp events queue is empty");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (_event instanceof SignUpEvent){
            this.events.remove(_event);
            return (LoginInformation)_event.fireEvent();
        } else {
            try{
                throw new Exception("This event is not an instance of SignUpEvent");
            } catch (Exception  e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * This method will remove the first event object on top of the queue and perform fireEvent action from that event object.
     * @return LoginInformation
     */
    @Override
    public LoginInformation fireEvent() {
        if(this.events.isEmpty()) {
            try {
                throw new Exception("The SignUp event queue is empty");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Event event = events.remove();
            return (LoginInformation) event.fireEvent();
        }
        return null;
    }
}
