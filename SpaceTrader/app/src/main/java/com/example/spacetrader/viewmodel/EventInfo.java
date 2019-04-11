package com.example.spacetrader.viewmodel;

public class EventInfo {
    String eventTitle;
    String eventDescription;
    boolean done;
    boolean trading;

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
