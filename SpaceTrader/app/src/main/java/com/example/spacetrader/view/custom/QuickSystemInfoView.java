package com.example.spacetrader.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.spacetrader.R;
/**
 * Class for System info view
 */
public class QuickSystemInfoView extends FrameLayout {

    private Button warpButton;
    private TextView nameText;
    private TextView locationText;
    private TextView techLevelText;
    private TextView resourcesText;
    private TextView noFuelText;

    /**
     * Initializes the system info view
     * @param context the context
     * @param attrs the attribute set
     */
    public QuickSystemInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    /**
     * Initializes the system info view
     * @param context the context
     * @param attrs the attribute set
     * @param defStyleAttr the style
     */
    public QuickSystemInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    /**
     * Initializes the system info view
     */
    private void init() {
        inflate(getContext(), R.layout.quick_system_info_view, this);

        warpButton = findViewById(R.id.qsi_warp_button);
        nameText = findViewById(R.id.qsi_system_name);
        locationText = findViewById(R.id.qsi_location);
        techLevelText = findViewById(R.id.qsi_techlevel);
        resourcesText = findViewById(R.id.qsi_resources);
        noFuelText = findViewById(R.id.qsi_no_fuel);
    }
    /**
     * Sets the attributes of the view
     * @param planetName the planet name
     * @param loc_x the x coordinate of the planet
     * @param loc_y the y coordinate of the planet
     * @param techLevel the tech level of the planet
     * @param resources the resources on the planet
     */
    public void setAttributes(String planetName, int loc_x, int loc_y, String techLevel, String resources) {
        nameText.setText(planetName);
        locationText.setText(String.format(getResources().getString(R.string.qsi_location), loc_x, loc_y));
        techLevelText.setText(String.format(getResources().getString(R.string.qsi_tech), techLevel));
        resourcesText.setText(String.format(getResources().getString(R.string.qsi_resources), resources));
    }

    /**
     * Sets the planet as not available if out of range
     * @param notEnoughFuel whether or not the planet is within range
     */
    public void setNotEnoughFuel(boolean notEnoughFuel) {
        warpButton.setEnabled(!notEnoughFuel);
        noFuelText.setAlpha(notEnoughFuel ? 1.0f : 0.0f);
    }
    /**
     * Sets the warp handler
     * @param listener the onclick listener
     */
    public void setWarpHandler(View.OnClickListener listener) {
        warpButton.setOnClickListener(listener);
    }
}
