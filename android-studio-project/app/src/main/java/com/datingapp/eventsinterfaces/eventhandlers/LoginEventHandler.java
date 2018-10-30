package com.datingapp.eventsinterfaces.eventhandlers;

import com.datingapp.eventsinterfaces.events.EventListener;

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


    private Queue<EventListener> events = new LinkedList<>();


    @Override
    public void addEvent(EventListener _event) {
        this.events.add(_event);
    }


    @Override
    public void fireAllEvents() {
        if(events.isEmpty()) {
            System.out.println("The login queue is empty");
        } else {
            while(!events.isEmpty()) {
                EventListener eventListener = events.remove();
                eventListener.fireEvent();
            }
        }
    }


    @Override
    public void fireEvent(EventListener _event) {
        _event.fireEvent();
        this.events.remove(_event);
    }



    @Override
    public void fireEvent() {
        EventListener event = events.remove();
        event.fireEvent();
    }
}
