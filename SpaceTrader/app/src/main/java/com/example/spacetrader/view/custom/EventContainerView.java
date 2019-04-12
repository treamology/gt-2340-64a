package com.example.spacetrader.view.custom;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.example.spacetrader.R;
/**
 * Class for Event ContainerView
 */
public class EventContainerView extends ConstraintLayout {

    private FrameLayout eventFrame;

    /**
     * Initializes the Event ContainerView
     * @param context the context of the view
     * @param attrs the attributes
     */
    public EventContainerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    /**
     * Initializes the Event ContainerView
     * @param context the context of the view
     * @param attrs the attributes
     * @param defStyleAttr the style
     */
    public EventContainerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    /**
     * Initializes the event frame
     */
    private void init() {
        inflate(getContext(), R.layout.event_overlay, this);

        eventFrame = findViewById(R.id.eventFrame);
    }
    /**
     * Gets the event frame
     * @return the layout of the frame
     */
    public FrameLayout getEventFrame() {
        return eventFrame;
    }
}
