package com.datingapp.eventsinterfaces.eventhandlers;
/**
 * @Author:Vincent
 *
 * @Date:11/25/2018
 */

import com.datingapp.eventsinterfaces.events.Event;
import com.datingapp.eventsinterfaces.events.MatchEvent;
import com.datingapp.shared.dataobjects.Match;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MatchEventHandler implements EventHandler<Match> {
    private static MatchEventHandler instance = null;


    public static MatchEventHandler getInstance() {
        if(MatchEventHandler.instance == null) {
            MatchEventHandler.instance = new MatchEventHandler();
        }
        return MatchEventHandler.instance;
    }


    private Queue<Event> events = new LinkedList<>();


    @Override
    public void addEvent(Event _event) {
        if(_event instanceof MatchEvent) {
            events.add(_event);
        } else {
            try {
                throw new Exception("Wrong events");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    @Override
    public ArrayList<Match> fireAllEvents() {
        ArrayList<Match> matchesRecord = new ArrayList<>();
        if(events.isEmpty()) {
            try{
                throw new Exception("Queue is empty.");
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        } else {
            while(events.isEmpty()) {
                MatchEvent event = (MatchEvent) events.remove();
                matchesRecord.add(event.fireEvent());
            }
        }
        return matchesRecord;
    }


    @Override
    public Match fireEvent(Event _event) {
        if(events.isEmpty()) {
            try{
                throw new Exception("Empty Queue");
            } catch(Exception ex) {
                ex.printStackTrace();
            } finally {
                return null;
            }
        } else {
            events.remove(_event);
            return (Match) _event.fireEvent();
        }
    }


    @Override
    public Match fireEvent() {
        if(events.isEmpty()) {
            try{

            } catch(Exception ex) {
                ex.printStackTrace();
            } finally {
                return null;
            }
        } else {
            MatchEvent event = (MatchEvent)events.remove();
            Match match = event.fireEvent();
            return match;
        }
    }
}
