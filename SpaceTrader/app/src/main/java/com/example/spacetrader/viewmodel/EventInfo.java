package com.example.spacetrader.viewmodel;

public class EventInfo {
    private final String eventTitle;
    private final String eventDescription;
    private final boolean done;
    private final boolean trading;

    public EventInfo(String eventTitle, String eventDescription, boolean trading, boolean done) {
        this.eventTitle = eventTitle;
        this.eventDescription = eventDescription;
        this.trading = trading;
        this.done = done;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public boolean getTrading() {
        return trading;
    }

    public boolean getDone() {
        return done;
    }
}
