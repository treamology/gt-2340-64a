package com.example.spacetrader.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.model.Player;

import java.util.stream.IntStream;

public class ConfigurationActivity extends AppCompatActivity {

    private EditText nameTextbox;
    private TextView skillPointsTextview;

    private int[] skillPoints = new int[Player.Skill.values().length];
    private Button[] plusButtons = new Button[Player.Skill.values().length];
    private Button[] minusButtons = new Button[Player.Skill.values().length];
    private TextView[] pointTextViews = new TextView[Player.Skill.values().length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuration_activity);


        nameTextbox = findViewById(R.id.nameTextbox);
        skillPointsTextview = findViewById(R.id.skillPointsText);

        plusButtons[0] = findViewById(R.id.pilotPlusButton);
        minusButtons[0] = findViewById(R.id.pilotMinusButton);
        pointTextViews[0] = findViewById(R.id.pilotPoints);
        plusButtons[1] = findViewById(R.id.fighterPlusButton);
        minusButtons[1] = findViewById(R.id.fighterMinusButton);
        pointTextViews[1] = findViewById(R.id.fighterPoints);
        plusButtons[2] = findViewById(R.id.traderPlusButton);
        minusButtons[2] = findViewById(R.id.traderMinusButton);
        pointTextViews[2] = findViewById(R.id.traderPoints);
        plusButtons[3] = findViewById(R.id.engineerPlusButton);
        minusButtons[3] = findViewById(R.id.engineerMinusButton);
        pointTextViews[3] = findViewById(R.id.engineerPoints);

        setSupportActionBar((Toolbar)findViewById(R.id.newGameToolbar));

        for (int i = 0; i < plusButtons.length; i++) {
            final int finali = i; // this is stupid
            plusButtons[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    changeSkillPoints(1, Player.Skill.values()[finali]);
                }
            });
        }
        for (int i = 0; i < minusButtons.length; i++) {
            final int finali = i;
            minusButtons[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    changeSkillPoints(-1, Player.Skill.values()[finali]);
                }
            });
        }

        skillPointsTextview.setText(getSkillPointsString());
    }

    void changeSkillPoints(int byAmount, Player.Skill skillType) {
        // Do nothing if we're already at the max skill point amount, or would go below zero.
        if (getTotalSkill() + byAmount > Player.getSkillPointMax()
            || skillPoints[skillType.ordinal()] + byAmount < 0) {
            return;
        }
        skillPoints[skillType.ordinal()] += byAmount;
        pointTextViews[skillType.ordinal()].setText(Integer.toString(skillPoints[skillType.ordinal()]));
        skillPointsTextview.setText(getSkillPointsString());
    }

    int getTotalSkill() {
        int total = 0;
        for (int points : skillPoints) {
            total += points;
        }
        return total;
    }

    String getSkillPointsString() {
        return String.format(getResources().getString(R.string.skill_points_option),
                             Player.getSkillPointMax() - getTotalSkill());
    }

}
