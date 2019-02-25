package com.example.spacetrader.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.model.system.TechLevel;
import com.example.spacetrader.viewmodel.SystemInfoViewModel;

public class SystemInfoFragment extends Fragment {

    private SystemInfoViewModel mViewModel;

    private TextView systemNameTextView;
    private TextView positionTextView;
    private TextView resourcesTextView;
    private TextView techLevelTextView;

    public static SystemInfoFragment newInstance() {
        return new SystemInfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.planet_info_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SystemInfoViewModel.class);

        View view = getView();
        systemNameTextView = view.findViewById(R.id.systemName);
        positionTextView = view.findViewById(R.id.locationValue);
        resourcesTextView = view.findViewById(R.id.resourcesValue);
        techLevelTextView = view.findViewById(R.id.techLevelValue);

        systemNameTextView.setText(mViewModel.getSystemName());
        positionTextView.setText(String.format("%d, %d", mViewModel.getSystemX(), mViewModel.getSystemY()));
        resourcesTextView.setText(mViewModel.getResourceBias());
        techLevelTextView.setText(mViewModel.getTechLevel());
    }

}
