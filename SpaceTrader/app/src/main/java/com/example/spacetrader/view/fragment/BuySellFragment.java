package com.example.spacetrader.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.view.custom.TradeGoodItemView;
import com.example.spacetrader.viewmodel.BuySellViewModel;
import com.example.spacetrader.viewmodel.TradeGoodInfo;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class BuySellFragment extends Fragment implements GameFragment {

    private BuySellViewModel mViewModel;

    private TextView remainingCargoText;
    private TextView creditsText;

    public static BuySellFragment newInstance() {
        return new BuySellFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.buy_sell_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BuySellViewModel.class);

        refreshInfo();

        View view = getView();
        final LinearLayout verticalLayout = view.findViewById(R.id.tradeItemsLayout);
        final LinkedHashMap<String, TradeGoodInfo> goods = mViewModel.getGoods();
        final TabLayout shopTabBar = view.findViewById(R.id.shopTabBar);

        // Wire up buy/sell buttons to change the shop mode.
        shopTabBar.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                for (int i = 0; i < goods.size(); i++) {
                    TradeGoodItemView itemView = (TradeGoodItemView)verticalLayout.getChildAt(i);
                    TradeGoodInfo item = goods.get(goods.keySet().toArray()[i]);
                    updateItemView(itemView, item, tab.getPosition());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        remainingCargoText = view.findViewById(R.id.remainingCargoText);
        creditsText = view.findViewById(R.id.shopCashText);

        refreshInfo();
        updateStatusText();
    }

    private void updateItemView(TradeGoodItemView itemView, TradeGoodInfo item, int tabBarIndex) {
        switch (tabBarIndex) {
            case 0:
                itemView.setAttributes(item.getName(), item.getPrice(), item.getQuantity(), "BUY", getResources().getString(R.string.shop_quantity_buy_suffix));
                break;
            case 1:
                itemView.setAttributes(item.getName(), item.getPrice(), item.getShipQuantity(), "SELL", getResources().getString(R.string.shop_quantity_sell_suffix));
                break;
            default:
                break;
        }
    }

    private void updateStatusText() {
        updateStatusText(mViewModel.getAvailableCargo(), mViewModel.getTotalCargo(), mViewModel.getCash());
    }

    private void updateStatusText(int cargoRemaining, int totalCargo, int numCredits) {
        remainingCargoText.setText(String.format(getResources().getString(R.string.shop_remaining_cargo), cargoRemaining, totalCargo));
        creditsText.setText(String.format(getResources().getString(R.string.shop_cash), numCredits));
    }

    @Override
    public void refreshInfo() {
        View view = getView();
        final LinearLayout verticalLayout = view.findViewById(R.id.tradeItemsLayout);
        final Map<String, TradeGoodInfo> goods = mViewModel.getGoods();
        final TabLayout shopTabBar = view.findViewById(R.id.shopTabBar);

        Iterator<String> it = goods.keySet().iterator();
        while (it.hasNext()) {
            final TradeGoodInfo good = goods.get(it.next());
            final TradeGoodItemView tradeGoodItemView = new TradeGoodItemView(getContext(), null);

            updateItemView(tradeGoodItemView, good, 0);

            tradeGoodItemView.getActionButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (shopTabBar.getSelectedTabPosition() == 0) {
                        mViewModel.buyItem(good, 1);
                    } else {
                        mViewModel.sellItem(good, 1);
                    }

                    updateItemView(tradeGoodItemView, good, shopTabBar.getSelectedTabPosition());
                    updateStatusText();
                }
            });
            verticalLayout.addView(tradeGoodItemView, verticalLayout.getChildCount());
        }
    }

}
