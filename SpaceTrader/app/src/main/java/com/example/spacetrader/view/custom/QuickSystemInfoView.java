package com.example.spacetrader.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.spacetrader.R;

public class QuickSystemInfoView extends FrameLayout {

    private Button warpButton;
    private TextView nameText;
    private TextView locationText;
    private TextView techLevelText;
    private TextView resourcesText;
    private TextView noFuelText;

    public QuickSystemInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QuickSystemInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.quick_system_info_view, this);

        warpButton = findViewById(R.id.qsi_warp_button);
        nameText = findViewById(R.id.qsi_system_name);
        locationText = findViewById(R.id.qsi_location);
        techLevelText = findViewById(R.id.qsi_techlevel);
        resourcesText = findViewById(R.id.qsi_resources);
        noFuelText = findViewById(R.id.qsi_no_fuel);
    }

    public void setAttributes(String planetName, int loc_x, int loc_y, String techLevel, String resources) {
        nameText.setText(planetName);
        locationText.setText(String.format(getResources().getString(R.string.qsi_location), loc_x, loc_y));
        techLevelText.setText(String.format(getResources().getString(R.string.qsi_tech), techLevel));
        resourcesText.setText(String.format(getResources().getString(R.string.qsi_resources), resources));
    }

    public void setNotEnoughFuel(boolean notEnoughFuel) {
        warpButton.setEnabled(!notEnoughFuel);
        noFuelText.setAlpha(notEnoughFuel ? 1.0f : 0.0f);
    }

    public void setWarpHandler(View.OnClickListener listener) {
        warpButton.setOnClickListener(listener);
    }
}
