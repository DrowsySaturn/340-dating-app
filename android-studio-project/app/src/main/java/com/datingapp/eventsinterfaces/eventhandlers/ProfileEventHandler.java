package com.datingapp.eventsinterfaces.eventhandlers;
/**
 * This is an instance of profile event handler, it stores in the profile events.
 * @Author:VincentYang
 * @Date:12/3/2018
 */

import com.datingapp.eventsinterfaces.events.Event;
import com.datingapp.eventsinterfaces.events.ProfileEvent;
import com.datingapp.shared.dataobjects.Profile;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ProfileEventHandler implements EventHandler<Profile> {


    /**
     * This is an singleton design pattern. This will return an instance of ProfileEventHandler.
     */
    //////////////////////////////////////////////////////////////////////////
    private static ProfileEventHandler instance = null;


    public static ProfileEventHandler getInstance() {
        if(ProfileEventHandler.instance == null) {
            return ProfileEventHandler.instance = new ProfileEventHandler();
        } else {
            return ProfileEventHandler.instance;
        }
    }
    /////////////////////////////////////////////////////////////////////////


    /**
     * This is an event queue, it holds the event object of ProfileEvent.
     */
    private Queue<Event> events = new LinkedList<>();


    /**
     * This method checks if the passed in event is an instance of ProfileEvent. Then stores the event into the queue.
     * @param _event
     */
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


    /**
     * This will remove all the event objects within the queue, and perform fireEvent on every single event objects.
     * @return ArrayList<Profile>.
     */
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


    /**
     * This method will force fire a selected event. DO NOT USE, IF NOT NECESSARY.
     * @param _event
     * @return Profile.
     */
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


    /**
     * This method will remove the first event on top of the queue, and perform fireEvent action.
     * @return Profile.
     */
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
}
