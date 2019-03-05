package com.example.spacetrader.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

    public enum ShopMode {
        BUY, SELL
    }

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

        View view = getView();
        LinearLayout verticalLayout = view.findViewById(R.id.tradeItemsLayout);
        List<DisplayedTradeGood> goods = mViewModel.getGoods();

        for (DisplayedTradeGood good : goods) {
            TradeGoodItemView tradeGoodItemView = new TradeGoodItemView(getContext(), null);
            tradeGoodItemView.setAttributes(good.getName(), good.getPrice(), good.getQuantity(), ShopMode.BUY);
            verticalLayout.addView(tradeGoodItemView, verticalLayout.getChildCount());
        }
    }

}
