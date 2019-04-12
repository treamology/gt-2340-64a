package com.example.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.spacetrader.model.GameState;
import com.example.spacetrader.model.system.SolarSystem;
/**
 * System Info View Model Class
 */
public class SystemInfoViewModel extends ViewModel {

    private final SolarSystem currentSystem;

    /**
     * Initializes the system info view model
     */
    public SystemInfoViewModel() {
        super();
        this.currentSystem = GameState.getState().getPlayer().getCurrentSystem();
    }
    /**
     * Gets the system name
     * @return the system name
     */
    public String getSystemName() {
        return currentSystem.getName();
    }

    /**
     * Gets the system x-coordinate
     * @return the system x-coordinate
     */
    public int getSystemX() {
        return currentSystem.getPosition().getX();
    }

    /**
     * Gets the system y-coordinate
     * @return the system y-coordinate
     */
    public int getSystemY() {
        return currentSystem.getPosition().getY();
    }

    /**
     * Gets the system resource bias
     * @return the system resource bias
     */
    public String getResourceBias() {
        return currentSystem.getResourceBias().name;
    }

    /**
     * Gets the system tech level
     * @return the system tech level
     */
    public String getTechLevel() {
        return currentSystem.getTechLevel().name;
    }

    /**
     * Gets the system's current Price Increase Event
     * @return the system's current Price Increase Event
     */
    public String getCurrentIncreaseEvent() {
        return currentSystem.getCurrentIncreaseEvent().name;
    }

    /**
     * Gets the system's image index
     * @return the system's image index
     */
    public int getImageIndex() { return currentSystem.getImageIndex(); }
}
