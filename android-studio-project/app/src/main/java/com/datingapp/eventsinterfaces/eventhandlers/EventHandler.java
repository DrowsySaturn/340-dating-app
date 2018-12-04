package eventsinterfaces.eventhandlers;
/**
 * This is an interface to handle the event type objects.
 * @Author:VincentYang
 * @Date:12/3/2018
 */

import com.datingapp.eventsinterfaces.events.Event;
import java.util.ArrayList;

public interface EventHandler<T> {
    public void addEvent(Event _event);
    public T fireEvent(Event _event);
    public T fireEvent();
    public ArrayList<T> fireAllEvents();
}
