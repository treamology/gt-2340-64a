package com.example.spacetrader.viewmodel;
/**
 * The event info class
 */
public class EventInfo {
    private final String eventTitle;
    private final String eventDescription;
    private final boolean done;
    private final boolean trading;
    /**
     * Initializes the Event info
     * @param eventTitle the event title
     * @param eventDescription the event description
     * @param trading whether the event is trading
     * @param done whether the event is done or not
     */
    public EventInfo(String eventTitle, String eventDescription, boolean trading, boolean done) {
        this.eventTitle = eventTitle;
        this.eventDescription = eventDescription;
        this.trading = trading;
        this.done = done;
    }
    /**
     * Gets the event title
     * @return the event title
     */
    public String getEventTitle() {
        return eventTitle;
    }

    /**
     * Gets the event description
     * @return the event description
     */
    public String getEventDescription() {
        return eventDescription;
    }

    /**
     * Gets whether the event is trading
     * @return whether the event is trading
     */
    public boolean getTrading() {
        return trading;
    }

    /**
     * Gets whether the event is done
     * @return the event title
     */
    public boolean getDone() {
        return done;
    }
}
