package com.example.spacetrader.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.SparseArray;
import android.view.MenuItem;

import com.example.spacetrader.R;
import com.example.spacetrader.view.fragment.SystemInfoFragment;
import com.example.spacetrader.view.fragment.UniverseFragment;

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

        fragmentMap.put(R.id.navigation_planet_info, new SystemInfoFragment());
        fragmentMap.put(R.id.navigation_warp, new UniverseFragment());

        replaceCurrentFragment(fragmentMap.get(R.id.navigation_planet_info));
    }

    private void replaceCurrentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrame, fragment)
                .commit();
    }

}
