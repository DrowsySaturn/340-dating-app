package com.datingapp.eventsinterfaces.eventhandlers;

import com.datingapp.eventsinterfaces.events.Event;
import com.datingapp.eventsinterfaces.events.LoginEvent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class LoginEventHandler implements EventHandler<Boolean> {
    private static LoginEventHandler instance = null;


    public static LoginEventHandler getInstance() {
        if (instance == null) {
            LoginEventHandler.instance = new LoginEventHandler();
        }
        return LoginEventHandler.instance;
    }


    private Queue<Event> events = new LinkedList<>();


    @Override
    public void addEvent(Event _event) {
        if(_event instanceof LoginEvent) {
            try {
                throw new Exception("This event is not an instance of LoginEvent");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.events.add(_event);
        }
    }


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
            Event event = events.remove();
            return new Boolean((boolean) event.fireEvent());
        }
    }
}
