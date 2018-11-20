package com.datingapp.eventsinterfaces.eventhandlers;

import com.datingapp.eventsinterfaces.events.Event;
import com.datingapp.eventsinterfaces.events.LoginEvent;

import java.util.LinkedList;
import java.util.Queue;

public class LoginEventHandler implements EventHandler {
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
    public void fireAllEvents() {
        if(events.isEmpty()) {
            try {
                throw new Exception("The Login events queue is empty");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            while(!events.isEmpty()) {
                Event eventListener = events.remove();
                eventListener.fireEvent();
            }
        }
    }


    @Override
    public void fireEvent(Event _event) {
        if(this.events.isEmpty()) {
            try {
                throw new Exception("The Login events queue is empty");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(_event instanceof LoginEvent) {
            this.events.remove(_event);
            _event.fireEvent();
        } else {
            try {
                throw new Exception("The event is not an instance of LoginEvent");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public void fireEvent() {
        Event event = events.remove();
        event.fireEvent();
    }
}
