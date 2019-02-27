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
import com.example.spacetrader.view.custom.SpaceMap;
import com.example.spacetrader.viewmodel.UniverseViewModel;

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

        SpaceMap spaceMap = getView().findViewById(R.id.spaceMap);
        spaceMap.setViewModel(mViewModel);
    }

}
