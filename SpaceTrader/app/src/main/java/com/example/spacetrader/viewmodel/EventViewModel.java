package com.example.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.spacetrader.model.event.Event;
import com.example.spacetrader.model.event.TraderEvent;

public class EventViewModel extends ViewModel {

    Event currentEvent;

    public void generateNewEvent() {
        currentEvent = Event.randomEvent();
        currentEvent.run();
    }

    public EventInfo getEventInfo() {
        return new EventInfo(String.format("A %s approaches!", currentEvent.getName()), currentEvent.getDescription(), false, false);
    }

    public EventInfo attack() {
        currentEvent.attack();
        return new EventInfo(String.format("You attack the %s", currentEvent.getName()), currentEvent.getConsequence(), false, true);
    }

    public EventInfo flee() {
        currentEvent.flee();
        return new EventInfo(String.format("You flee the %s", currentEvent.getName()), currentEvent.getConsequence(), false, true);
    }

    public EventInfo talk() {
        currentEvent.talk();
        boolean trading = currentEvent.getClass() == TraderEvent.class;
        return new EventInfo(String.format("You talk to the %s", currentEvent.getName()), currentEvent.getConsequence(), trading, true);
    }



}
