package com.example.spacetrader.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.view.fragment.PlanetInfoFragment;
import com.example.spacetrader.view.fragment.UniverseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameActivity extends FragmentActivity {
    private SparseArray<Fragment> fragmentMap = new SparseArray<>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
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

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Menu menu = navigation.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            switch (item.getItemId()) {
                case R.id.navigation_planet_info:
                    fragmentMap.put(R.id.navigation_planet_info, new PlanetInfoFragment());
                case R.id.navigation_warp:
                    fragmentMap.put(R.id.navigation_warp, new UniverseFragment());
            }
        }

        replaceCurrentFragment(fragmentMap.get(R.id.navigation_planet_info));
    }

    private void replaceCurrentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrame, fragment)
                .commit();
    }

}
