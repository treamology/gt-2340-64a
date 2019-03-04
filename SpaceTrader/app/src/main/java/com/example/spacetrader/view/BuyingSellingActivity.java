package com.example.spacetrader.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.spacetrader.R;
import com.example.spacetrader.viewmodel.*;
import com.example.spacetrader.model.*;

public class BuyingSellingActivity extends AppCompatActivity {

    CargoBay cargoBay;
    CargoBay[] cargoArray = Ship.getCargoArray();
    int equipmentCount = cargoBay.getCount();
    /*
     @param good the good that is bought
     */
    void buy(TradeGood good) {
        for (int i = 0; i < cargoArray.length; i++) {
            if (cargoArray[i].getType().equals(good)) {
                cargoBay = cargoArray[i];
                break;
            }
        }

        if (cargoBay.getCount() == cargoBay.getCapacity()) {
            Toast errorMsg = Toast.makeText(getApplicationContext(), "Carbo Bay is full.", Toast.LENGTH_SHORT);
            errorMsg.show();
            return;
        } else {
            equipmentCount++;
        }
    }

    void sell(TradeGood good) {
        for (int i = 0; i < cargoArray.length; i++) {
            if (cargoArray[i].getType().equals(good)) {
                cargoBay = cargoArray[i];
                break;
            }
        }
        equipmentCount--;
    }
}