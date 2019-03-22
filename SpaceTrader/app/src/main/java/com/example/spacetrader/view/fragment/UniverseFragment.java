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
import com.example.spacetrader.viewmodel.modeldisplay.SolarSystemInfo;

public class UniverseFragment extends Fragment {

    private UniverseViewModel mViewModel;

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

        final SpaceMapView spaceMapView = getView().findViewById(R.id.spaceMap);
        spaceMapView.setViewModel(mViewModel);

        final QuickSystemInfoView systemInfoView = getView().findViewById(R.id.quickSystemInfo);
        systemInfoView.setAlpha(0);
        spaceMapView.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SolarSystemInfo info = spaceMapView.getLastTouchedSystem();
                systemInfoView.animate()
                        .alpha(1.0f)
                        .setDuration(100);
                systemInfoView.setAttributes(info.name, info.x, info.y, info.techLevel, info.resources);
                systemInfoView.setNotEnoughFuel(mViewModel.getCurrentShipFuel() < mViewModel.getSystemDistanceFromPlayer(info));
            }
        });
    }

}
