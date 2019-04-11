package com.example.spacetrader.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.viewmodel.SystemInfoViewModel;

import java.util.Locale;
import java.util.Objects;

public class SystemInfoFragment extends Fragment implements GameFragment {

    private SystemInfoViewModel mViewModel;

    private TextView systemNameTextView;
    private TextView positionTextView;
    private TextView resourcesTextView;
    private TextView techLevelTextView;
    private TextView increaseEventTextView;
    private ImageView systemImageView;

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
        systemNameTextView = Objects.requireNonNull(view).findViewById(R.id.systemName);
        positionTextView = view.findViewById(R.id.locationValue);
        resourcesTextView = view.findViewById(R.id.resourcesValue);
        techLevelTextView = view.findViewById(R.id.techLevelValue);
        increaseEventTextView = view.findViewById(R.id.increaseEventValue);
        systemImageView = view.findViewById(R.id.systemImage);

        refreshInfo();
    }

    @Override
    public void refreshInfo() {
        systemNameTextView.setText(mViewModel.getSystemName());
        positionTextView.setText(String.format(Locale.getDefault(),"%d, %d", mViewModel.getSystemX(), mViewModel.getSystemY()));
        resourcesTextView.setText(mViewModel.getResourceBias());
        techLevelTextView.setText(mViewModel.getTechLevel());
        increaseEventTextView.setText(mViewModel.getCurrentIncreaseEvent());

        int imageID = getResources().getIdentifier(String.format(Locale.getDefault(), "planet%d", mViewModel.getImageIndex()), "drawable", "com.example.spacetrader");
        BitmapDrawable image = (BitmapDrawable)getResources().getDrawable(imageID);
        image.getPaint().setFilterBitmap(false);
        systemImageView.setImageDrawable(image);
    }

}
