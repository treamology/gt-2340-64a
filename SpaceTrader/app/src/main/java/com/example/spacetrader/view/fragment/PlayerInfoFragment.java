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
import com.example.spacetrader.viewmodel.PlayerInfoViewModel;

import java.util.Objects;

public class PlayerInfoFragment extends Fragment implements GameFragment {

    private TextView nameTextView;
    private TextView shipTextView;
    private TextView cashTextView;
    private TextView fuelTextView;
    private TextView maxFuelTextView;

    private PlayerInfoViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.player_info_fragment, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(PlayerInfoViewModel.class);

        final View view = getView();
        nameTextView = Objects.requireNonNull(view).findViewById(R.id.playerinfo_name);
        shipTextView = view.findViewById(R.id.playerinfo_ship_type);
        cashTextView = view.findViewById(R.id.playerinfo_cash);
        fuelTextView = view.findViewById(R.id.playerinfo_fuel);
        maxFuelTextView = view.findViewById(R.id.playerinfo_max_fuel);
        Button buy1Button = view.findViewById(R.id.playerinfo_buy1_button);
        Button buyAllButton = view.findViewById(R.id.playerinfo_buyall_button);

        buy1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.add1Fuel();
                refreshInfo();
            }
        });
        buyAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.maxOutFuel();
                refreshInfo();
            }
        });
        refreshInfo();
    }

    @Override
    public void refreshInfo() {
        nameTextView.setText(viewModel.getPlayerName());
        shipTextView.setText(String.format(getResources().getString(R.string.pi_shiptype), viewModel.getShipName()));
        cashTextView.setText(String.format(getResources().getString(R.string.pi_cash), viewModel.getPlayerCash()));
        fuelTextView.setText(String.format(getResources().getString(R.string.pi_fuel), viewModel.getShipFuel()));
        maxFuelTextView.setText(String.format(getResources().getString(R.string.pi_max_fuel), viewModel.getMaxShipFuel()));
    }
}
