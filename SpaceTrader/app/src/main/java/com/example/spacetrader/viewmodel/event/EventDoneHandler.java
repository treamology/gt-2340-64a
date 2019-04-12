package com.example.spacetrader.viewmodel.event;
/**
 * The interface for event done
 */
public interface EventDoneHandler {

    /**
     * handles what happens when an event is done
     * @param trading the trading
     */
    void handleEventDone(boolean trading);
}
