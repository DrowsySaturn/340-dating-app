package eventsinterfaces.events;

/**
 * This interface is provided for event type classes that implements it.
 * @Author:VincentYang
 * @param <T>: this is a generic type.
 */

public interface  Event<T> {
    /**
     * This is an interface method that output an action.
     * @return T: generic type.
     */
    public T fireEvent();
}
