package com.example.spacetrader.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spacetrader.R;
import com.example.spacetrader.view.custom.QuickSystemInfoView;
import com.example.spacetrader.view.custom.SpaceMapView;
import com.example.spacetrader.viewmodel.UniverseViewModel;
import com.example.spacetrader.viewmodel.event.GameEvents;
import com.example.spacetrader.viewmodel.SolarSystemInfo;

import java.util.Objects;
/**
 * The Universe fragment class
 */
public class UniverseFragment extends Fragment implements GameFragment {

    private UniverseViewModel mViewModel;
    /**
     * Creates a new instance of the Universe fragment
     * @return a new instance of the universe fragment
     */
    public static UniverseFragment newInstance() {
        return new UniverseFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.universe_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UniverseViewModel.class);

        final SpaceMapView spaceMapView = Objects.requireNonNull(getView()).findViewById(R.id.spaceMap);
        spaceMapView.setViewModel(mViewModel);

        final QuickSystemInfoView systemInfoView = getView().findViewById(R.id.quickSystemInfo);
        systemInfoView.setAlpha(0);
        spaceMapView.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SolarSystemInfo info = spaceMapView.getLastTouchedSystem();
                systemInfoView.animate()
                        .alpha(1.0f)
                        .setDuration(100);
                systemInfoView.setAttributes(info.name, info.x, info.y, info.techLevel, info.resources);
                systemInfoView.setNotEnoughFuel(mViewModel.getCurrentShipFuel() < mViewModel.getSystemDistanceFromPlayer(info));
                systemInfoView.setWarpHandler(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GameEvents.warpToSystem(info.index);
                    }
                });
            }
        });
    }

    @Override
    public void refreshInfo() {

    }
}
