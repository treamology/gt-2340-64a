package com.example.spacetrader.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.viewmodel.EventInfo;
import com.example.spacetrader.viewmodel.EventViewModel;
import com.example.spacetrader.viewmodel.event.EventDoneHandler;

import java.util.Objects;

public class EventFragment extends Fragment {

    private TextView titleView;
    private TextView descriptionView;
    private Button attackButton;
    private Button fleeButton;
    private Button talkButton;
    private Button doneButton;
    private boolean trading;

    private EventDoneHandler eventHandler;

    private EventViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.event_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EventViewModel.class);

        View v = getView();
        titleView = Objects.requireNonNull(v).findViewById(R.id.ef_event_title);
        descriptionView = v.findViewById(R.id.ef_event_desc);
        attackButton = v.findViewById(R.id.ef_attack_button);
        fleeButton = v.findViewById(R.id.ef_flee_button);
        talkButton = v.findViewById(R.id.ef_talk_button);
        doneButton = v.findViewById(R.id.ef_done_button);

        mViewModel.generateNewEvent();
        updateText(mViewModel.getEventInfo());



        attackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateText(mViewModel.attack());
            }
        });
        fleeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateText(mViewModel.flee());
            }
        });
        talkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateText(mViewModel.talk());
            }
        });
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventHandler.handleEventDone(trading);
            }
        });
    }

    private void updateText(EventInfo info) {
        titleView.setText(info.getEventTitle());
        descriptionView.setText(info.getEventDescription());
        if (info.getDone()) {
            doneButton.setVisibility(View.VISIBLE);
            attackButton.setVisibility(View.INVISIBLE);
            fleeButton.setVisibility(View.INVISIBLE);
            talkButton.setVisibility(View.INVISIBLE);
            trading = info.getTrading();
        }
    }

    public void setDoneEventHandler(EventDoneHandler eventHandler) {
        this.eventHandler = eventHandler;
    }
}
