package eventsinterfaces.eventhandlers;
/**
 * This is an instance of longin event handler, it stores in the login events.
 * @Author:VincentYang
 * @Date:12/3/2018
 */

import com.datingapp.client.cachelibrary.LoginConfirmationCache;
import com.datingapp.eventsinterfaces.events.Event;
import com.datingapp.eventsinterfaces.events.LoginEvent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class LoginEventHandler implements EventHandler<Boolean> {
    /**
     * This chunk of codes returns an instance of LoginEventHandler, singleton design pattern.
     */
    ///////////////////////////////////////////////////////////////////////
    private static LoginEventHandler instance = null;


    public static LoginEventHandler getInstance() {
        if (instance == null) {
            LoginEventHandler.instance = new LoginEventHandler();
        }
        return LoginEventHandler.instance;
    }
    ///////////////////////////////////////////////////////////////////////


    /**
     * This is the events queue. It holds the event objects.
     */
    private Queue<Event> events = new LinkedList<>();

    /**
     * This method will check the incoming event object as an instance of LoginEvent, if the boolean value
     * is true, then the event will be added to the queue.
     * @param _event
     */
    @Override
    public void addEvent(Event _event) {
        if(!(_event instanceof LoginEvent)) {
            try {
                throw new Exception("This event is not an instance of LoginEvent");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.events.add(_event);
        }
    }


    /**
     * This method will empty the queue, and perform fireEvent in every single event objects, to check if the user's login is valid.
     * @return ArrayList<Boolean></Boolean>
     */
    @Override
    public ArrayList<Boolean> fireAllEvents() {
        ArrayList<Boolean> eventListArrayList = new ArrayList<>();
        if(events.isEmpty()) {
            try {
                throw new Exception("The Login events queue is empty");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            while(!events.isEmpty()) {
                Event eventListener = events.remove();
                eventListArrayList.add((Boolean) eventListener.fireEvent());
            }
        }
        return eventListArrayList;
    }


    /**
     * This method will force fire an event within the queue. DON'T USER IF YOU DON'T HAVE TO.
     * @param _event
     * @return
     */
    @Override
    public Boolean fireEvent(Event _event) {
        if(this.events.isEmpty()) {
            try {
                throw new Exception("The Login events queue is empty");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                return new Boolean(false);
            }
        } else if(_event instanceof LoginEvent) {
            this.events.remove(_event);
            //important, user's session is being cached here.
            LoginConfirmationCache.getInstance().recordSession(((LoginEvent) _event).getLoginConfirmation());
            return new Boolean((boolean) _event.fireEvent());
        } else {
            try {
                throw new Exception("The event is not an instance of LoginEvent");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                return new Boolean(false);
            }
        }
    }


    /**
     * This method will first the head of the queue event and return a Boolean object to classify the login status.
     * @return Boolean.
     */
    @Override
    public Boolean fireEvent() {
        if(events.isEmpty()) {
            try {
                throw new Exception("Empty queue");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                return new Boolean(false);
            }
        } else {
            LoginEvent event = (LoginEvent) events.remove();
            //important, user's session is being cached here.
            LoginConfirmationCache.getInstance().recordSession(event.getLoginConfirmation());
            return new Boolean((boolean) event.fireEvent());
        }
    }
}
