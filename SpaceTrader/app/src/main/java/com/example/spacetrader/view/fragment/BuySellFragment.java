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

import com.example.spacetrader.R;
import com.example.spacetrader.view.custom.TradeGoodItemView;
import com.example.spacetrader.viewmodel.BuySellViewModel;
import com.example.spacetrader.viewmodel.modeldisplay.DisplayedTradeGood;

import java.util.List;

public class BuySellFragment extends Fragment {

    private BuySellViewModel mViewModel;

    public static BuySellFragment newInstance() {
        return new BuySellFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.buy_sell_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BuySellViewModel.class);

        // Set up the UI to list trade goods.
        View view = getView();
        final LinearLayout verticalLayout = view.findViewById(R.id.tradeItemsLayout);
        final List<DisplayedTradeGood> goods = mViewModel.getGoods();

        for (DisplayedTradeGood good : goods) {
            TradeGoodItemView tradeGoodItemView = new TradeGoodItemView(getContext(), null);
            tradeGoodItemView.setAttributes(good.getName(), good.getPrice(), good.getQuantity(), BuySellViewModel.ShopMode.BUY);
            verticalLayout.addView(tradeGoodItemView, verticalLayout.getChildCount());
        }

        // Wire up buy/sell buttons to change the shop mode.
        TabLayout shopTabBar = view.findViewById(R.id.shopTabBar);
        shopTabBar.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                BuySellViewModel.ShopMode mode = tab.getPosition() == 0 ? BuySellViewModel.ShopMode.BUY : BuySellViewModel.ShopMode.SELL;
                for (int i = 0; i < goods.size(); i++) {
                    TradeGoodItemView itemView = (TradeGoodItemView)verticalLayout.getChildAt(i);
                    DisplayedTradeGood item = goods.get(i);
                    switch (mode) {
                        case BUY:
                            itemView.setAttributes(item.getName(), item.getPrice(), item.getQuantity(), BuySellViewModel.ShopMode.BUY);
                            break;
                        case SELL:
                            itemView.setAttributes(item.getName(), item.getPrice(), item.getShipQuantity(), BuySellViewModel.ShopMode.SELL);
                            break;
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
