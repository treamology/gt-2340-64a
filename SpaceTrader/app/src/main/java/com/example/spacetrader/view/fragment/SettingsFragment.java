package com.example.spacetrader.view.fragment;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.Button;
import android.os.Bundle;
import android.view.ViewGroup;
import com.example.spacetrader.R;
import com.example.spacetrader.model.GameState;
import com.example.spacetrader.model.Gnat;
import com.example.spacetrader.model.Player;
import com.example.spacetrader.model.Ship;
import com.google.gson.Gson;

import android.content.Context;

public class SettingsFragment extends Fragment implements GameFragment {

    private Button musicSettingsButton;
    private Button saveButton;
    private Button loadButton;
    private MediaPlayer mediaPlayer;
    private Player player;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.settings_fragment, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        saveButton = (Button) view.findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        loadButton = (Button) view.findViewById(R.id.load_button);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load();
            }
        });


        musicSettingsButton = (Button) view.findViewById(R.id.music_button2);
        mediaPlayer = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.customdesign);
        musicSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                }
            }
        });
    }

    public void save() {
        player = GameState.getState().getPlayer();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", player.getName());
        editor.putInt("pilot", player.getPilot());
        editor.putInt("fighter", player.getFighter());
        editor.putInt("trader", player.getTrader());
        editor.putInt("engineer", player.getEngineer());
        Gson gson = new Gson();
        String json = gson.toJson(player.getShip());
        editor.putString("ship", json);
        editor.putInt("credits", player.getCredits());
        editor.putInt("currentSystemIndex", player.getCurrentSystemIndex());
        editor.putInt("fuel", player.getShip().getCurrentFuel());
        editor.apply();
    }

    public void load() {
        player = GameState.getState().getPlayer();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        player.setName(sharedPreferences.getString("name", "Default"));
        player.setPilot(sharedPreferences.getInt("pilot", 0));
        player.setFighter(sharedPreferences.getInt("fighter", 0));
        player.setTrader(sharedPreferences.getInt("trader", 0));
        player.setEngineer(sharedPreferences.getInt("engineer", 0));
        Gson gson = new Gson();
        String json = sharedPreferences.getString("ship", "Default");
        player.setShip(gson.fromJson(json, Gnat.class));
        player.setCredits(sharedPreferences.getInt("credits", 0));
        player.setCurrentSystemIndex(sharedPreferences.getInt("currentSystemIndex", 0));
        player.getShip().setCurrentFuel(sharedPreferences.getInt("fuel", 0));
    }

    @Override
    public void refreshInfo() {

    }
}
