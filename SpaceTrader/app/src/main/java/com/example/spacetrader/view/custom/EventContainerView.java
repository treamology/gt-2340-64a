package com.example.spacetrader.view.custom;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.example.spacetrader.R;

public class EventContainerView extends ConstraintLayout {

    private FrameLayout eventFrame;

    public EventContainerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EventContainerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.event_overlay, this);

        eventFrame = findViewById(R.id.eventFrame);
    }

    public FrameLayout getEventFrame() {
        return eventFrame;
    }
}
