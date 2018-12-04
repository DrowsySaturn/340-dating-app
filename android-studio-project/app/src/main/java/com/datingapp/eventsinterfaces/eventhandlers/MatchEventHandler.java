package eventsinterfaces.eventhandlers;
/**
 * This is an instance of match event handler, it stores in the match events.
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
    /**
     * This is an example of singleton design pattern. It returns an instance of MatchEventHandler.
     */
    /////////////////////////////////////////////////////////////////////
    private static MatchEventHandler instance = null;


    public static MatchEventHandler getInstance() {
        if(MatchEventHandler.instance == null) {
            MatchEventHandler.instance = new MatchEventHandler();
        }
        return MatchEventHandler.instance;
    }
    /////////////////////////////////////////////////////////////////////


    /**
     * This is an event queue, it holds the event object of MatchEvent.
     */
    private Queue<Event> events = new LinkedList<>();


    /**
     * This method checks if the passed in event is an instance of MatchEvent. Then stores the event into the queue.
     * @param _event
     */
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


    /**
     * This method will empty the queue, and perform fireEvent action on every single event objects.
     * @return ArraList<Match>
     */
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


    /**
     * This method will force fire a selected event. DO NOT USE, IF NOT NECESSARY.
     * @param _event
     * @return Match.
     */
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


    /**
     * This method will remove the first event object on top of the queue and perform fireEvent action from that event object.
     * @return Match.
     */
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
