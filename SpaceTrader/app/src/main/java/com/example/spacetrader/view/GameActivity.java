package com.example.spacetrader.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;

import com.example.spacetrader.R;
import com.example.spacetrader.view.custom.EventContainerView;
import com.example.spacetrader.view.fragment.BuySellFragment;
import com.example.spacetrader.view.fragment.EventFragment;
import com.example.spacetrader.view.fragment.InfoContainerFragment;
import com.example.spacetrader.view.fragment.SettingsFragment;
import com.example.spacetrader.view.fragment.UniverseFragment;
import com.example.spacetrader.viewmodel.event.EventDoneHandler;
import com.example.spacetrader.viewmodel.event.GameEvents;
import com.example.spacetrader.viewmodel.event.WarpEventHandler;

public class GameActivity extends FragmentActivity implements WarpEventHandler, EventDoneHandler {
    private BottomNavigationView navigation;
    private final SparseArray<Fragment> fragmentMap = new SparseArray<>();
    private EventContainerView eventContainerView;

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            replaceCurrentFragment(fragmentMap.get(item.getItemId()));
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentMap.put(R.id.navigation_planet_info, new InfoContainerFragment());
        fragmentMap.put(R.id.navigation_warp, new UniverseFragment());
        fragmentMap.put(R.id.navigation_buysell, new BuySellFragment());
        fragmentMap.put(R.id.navigation_settings, new SettingsFragment());

        eventContainerView = findViewById(R.id.eventContainerView);
        eventContainerView.setVisibility(View.INVISIBLE);

        replaceCurrentFragment(fragmentMap.get(R.id.navigation_planet_info));

        GameEvents.addWarpEvent(this);
    }

    private void replaceCurrentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrame, fragment)
                .commit();
        getSupportFragmentManager().executePendingTransactions();
    }

    @Override
    public void onWarp() {
        eventContainerView.setVisibility(View.VISIBLE);
        EventFragment fragment = new EventFragment();
        fragment.setDoneEventHandler(this);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.eventFrame, fragment)
                .commit();

        InfoContainerFragment infoFragment = (InfoContainerFragment) fragmentMap.get(R.id.navigation_planet_info);
        replaceCurrentFragment(infoFragment);

        navigation.setSelectedItemId(R.id.navigation_planet_info);
    }

    @Override
    public void handleEventDone(boolean trading) {
        if (trading) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.eventFrame, new BuySellFragment())
                    .commitNow();
        } else {
            eventContainerView.setVisibility(View.INVISIBLE);
        }
    }
}
