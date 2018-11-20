package com.datingapp.eventsinterfaces.eventhandlers;

import com.datingapp.eventsinterfaces.events.Event;
import com.datingapp.eventsinterfaces.events.ProfileEvent;

import java.util.LinkedList;
import java.util.Queue;

public class ProfileEventHandler implements EventHandler {

    private static ProfileEventHandler instance = null;


    public static ProfileEventHandler getInstance() {
        if(ProfileEventHandler.instance == null) {
            return ProfileEventHandler.instance = new ProfileEventHandler();
        } else {
            return ProfileEventHandler.instance;
        }
    }


    private Queue<Event> events = new LinkedList<>();


    @Override
    public void addEvent(Event _event) {
        if(_event instanceof ProfileEvent) {
            this.events.add(_event);
        } else {
            try{
                throw new Exception("This event is not an instance of ProfileEvent");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void fireEvent(Event _event) {
        if(this.events.isEmpty()) {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(_event instanceof  ProfileEvent) {
            this.events.remove(_event);
            _event.fireEvent();
        } else {
            try {
                throw new Exception("This is not an instance of ProfileEvent");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void fireEvent() {
        if(this.events.isEmpty()) {
            try {
                throw new Exception("The Profile events queue is empty");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Event event = events.remove();
            event.fireEvent();
        }
    }


    @Override
    public void fireAllEvents() {
        if(events.isEmpty()) {
            System.out.println("The profile events is empty");
        } else {
            while(!events.isEmpty()) {
                Event event = events.remove();
                event.fireEvent();
            }
        }
    }
}
