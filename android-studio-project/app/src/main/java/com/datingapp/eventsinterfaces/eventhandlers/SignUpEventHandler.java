package com.datingapp.eventsinterfaces.eventhandlers;

import com.datingapp.eventsinterfaces.events.Event;
import com.datingapp.eventsinterfaces.events.SignUpEvent;
import com.datingapp.shared.dataobjects.LoginInformation;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SignUpEventHandler implements EventHandler {

    private static SignUpEventHandler instance = null;


    public static SignUpEventHandler getInstance() {
        if(instance == null) {
            return SignUpEventHandler.instance = new SignUpEventHandler();
        } else {
            return SignUpEventHandler.instance;
        }
    }


    private Queue<Event> events = new LinkedList<>();


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
