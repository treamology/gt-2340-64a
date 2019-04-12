package com.example.spacetrader.view.custom;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

import com.example.spacetrader.R;

/**
 * Trade Good Item View class
 */
public class TradeGoodItemView extends ConstraintLayout {

    private TextView itemNameTextView;
    private TextView itemDescriptionTextView;
    private Button buyButton;
    /**
     * Initializes the trade good view
     * @param context the context
     * @param attrs the attribute set
     */
    public TradeGoodItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    /**
     * Initializes the trade good view
     * @param context the context
     * @param attrs the attribute set
     * @param defStyleAttr the style
     */
    public TradeGoodItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    /**
     * Initializes the trade good view
     */
    private void init() {
        inflate(getContext(), R.layout.tradegood_item_view, this);

        itemNameTextView = findViewById(R.id.itemName);
        itemDescriptionTextView = findViewById(R.id.itemStatus);
        buyButton = findViewById(R.id.buyButton);
    }
    /**
     * Sets the trade good attributes
     * @param price the price
     * @param amount the amount
     * @param itemName the item name
     * @param buttonText the text on the button
     * @param suffixText the suffix text
     */
    public void setAttributes(String itemName, int price, int amount, String buttonText, String suffixText) {
        itemNameTextView.setText(itemName);
        itemDescriptionTextView.setText(String.format(getResources().getString(R.string.shop_item_status) + " " + suffixText, price, amount));
        buyButton.setText(buttonText);
    }
    /**
     * Gets the buy button
     * @return the buy button
     */
    public Button getActionButton() {
        return buyButton;
    }
}
