package com.datingapp.eventsinterfaces.eventhandlers;

import com.datingapp.eventsinterfaces.events.Event;
import com.datingapp.eventsinterfaces.events.ProfileEvent;
import com.datingapp.shared.dataobjects.Profile;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ProfileEventHandler implements EventHandler<Profile> {

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
    public Profile fireEvent(Event _event) {
        if(this.events.isEmpty()) {
            try {
                throw new Exception("The profile queue is empty");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                return null;
            }
        } else if(_event instanceof  ProfileEvent) {
            this.events.remove(_event);
            return (Profile) _event.fireEvent();
        } else {
            try {
                throw new Exception("This is not an instance of ProfileEvent");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                return null;
            }
        }
    }


    @Override
    public Profile fireEvent() {
        if(this.events.isEmpty()) {
            try {
                throw new Exception("The Profile events queue is empty");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                return null;
            }
        } else {
            Event event = events.remove();
            return (Profile)event.fireEvent();
        }
    }


    @Override
    public ArrayList<Profile> fireAllEvents() {
        ArrayList<Profile> profiles = new ArrayList<>();
        if(events.isEmpty()) {
            System.out.println("The profile events is empty");
        } else {
            while(!events.isEmpty()) {
                Event event = events.remove();
                profiles.add(((ProfileEvent) event).fireEvent());
            }
        }
        return profiles;
    }
}
