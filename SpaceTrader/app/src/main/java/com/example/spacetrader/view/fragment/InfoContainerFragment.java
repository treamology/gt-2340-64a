package com.example.spacetrader.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spacetrader.R;

import java.util.Objects;
/**
 * Class for InfoContainer Fragment
 */
public class InfoContainerFragment extends Fragment implements GameFragment {

    private TabLayout tabBar;
    private Toolbar toolbar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.info_container_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tabBar = Objects.requireNonNull(getView()).findViewById(R.id.info_tabs);
        toolbar = getView().findViewById(R.id.info_toolbar);

        tabBar.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                updateFrames();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        refreshInfo();
    }

    private void updateFrames() {
        Fragment fragment;
        if (tabBar.getSelectedTabPosition() == 0) {
            fragment = new SystemInfoFragment();
            toolbar.setTitle(R.string.info_system_info);
        } else {
            fragment = new PlayerInfoFragment();
            toolbar.setTitle(R.string.info_player_info);
        }
        Objects.requireNonNull(getFragmentManager()).beginTransaction()
                .replace(R.id.info_container, fragment)
                .commit();
    }

    @Override
    public void refreshInfo() {
        updateFrames();
    }
}
