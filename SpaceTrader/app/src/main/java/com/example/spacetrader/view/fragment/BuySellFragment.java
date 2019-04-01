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
import com.example.spacetrader.model.GameState;
import com.example.spacetrader.view.custom.TradeGoodItemView;
import com.example.spacetrader.viewmodel.BuySellViewModel;
import com.example.spacetrader.viewmodel.modeldisplay.DisplayedTradeGood;

import java.util.List;

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
        final List<DisplayedTradeGood> goods = mViewModel.getGoods();
        final TabLayout shopTabBar = view.findViewById(R.id.shopTabBar);

        // Wire up buy/sell buttons to change the shop mode.
        shopTabBar.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                BuySellViewModel.ShopMode mode = tab.getPosition() == 0 ? BuySellViewModel.ShopMode.BUY : BuySellViewModel.ShopMode.SELL;
                for (int i = 0; i < goods.size(); i++) {
                    TradeGoodItemView itemView = (TradeGoodItemView)verticalLayout.getChildAt(i);
                    DisplayedTradeGood item = goods.get(i);
                    updateItemView(itemView, item, mode);
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

        updateStatusText();
    }

    private void updateItemView(TradeGoodItemView itemView, DisplayedTradeGood item, BuySellViewModel.ShopMode mode) {
        switch (mode) {
            case BUY:
                itemView.setAttributes(item.getName(), item.getPrice(), item.getQuantity(), BuySellViewModel.ShopMode.BUY);
                break;
            case SELL:
                itemView.setAttributes(item.getName(), item.getPrice(), item.getShipQuantity(), BuySellViewModel.ShopMode.SELL);
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
        final List<DisplayedTradeGood> goods = mViewModel.getGoods();
        final TabLayout shopTabBar = view.findViewById(R.id.shopTabBar);

        for (int i = 0; i < goods.size(); i++) {
            final int index = i;
            final DisplayedTradeGood good = goods.get(index);
            final TradeGoodItemView tradeGoodItemView = new TradeGoodItemView(getContext(), null);
            tradeGoodItemView.setAttributes(good.getName(), good.getPrice(), good.getQuantity(), BuySellViewModel.ShopMode.BUY);
            tradeGoodItemView.getActionButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BuySellViewModel.ShopMode mode = shopTabBar.getSelectedTabPosition() == 0 ? BuySellViewModel.ShopMode.BUY : BuySellViewModel.ShopMode.SELL;
                    mViewModel.performShopAction(mode, index, 1);
                    updateItemView(tradeGoodItemView, good, mode);
                    updateStatusText();
                }
            });
            verticalLayout.addView(tradeGoodItemView, verticalLayout.getChildCount());
        }
    }

}
