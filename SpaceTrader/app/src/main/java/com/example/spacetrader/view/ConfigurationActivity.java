package com.example.spacetrader.view;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacetrader.R;
import com.example.spacetrader.model.GameState;
import com.example.spacetrader.model.Player;
import com.example.spacetrader.viewmodel.ConfigurationViewModel;

public class ConfigurationActivity extends AppCompatActivity {

    private EditText nameTextbox;
    private TextView skillPointsTextview;
    private Spinner difficultySpinner;
    private Button createGameButton;

    private int[] skillPoints = new int[Player.Skill.values().length];
    private Button[] plusButtons = new Button[Player.Skill.values().length];
    private Button[] minusButtons = new Button[Player.Skill.values().length];
    private TextView[] pointTextViews = new TextView[Player.Skill.values().length];

    private ConfigurationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuration_activity);

        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);

        nameTextbox = findViewById(R.id.nameTextbox);
        skillPointsTextview = findViewById(R.id.skillPointsText);
        difficultySpinner = findViewById(R.id.difficultySpinner);
        createGameButton = findViewById(R.id.createButton);

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

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Confirmation");
        builder.setMessage("Continue?");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                viewModel.createGame(nameTextbox.getText().toString(),
                        skillPoints[0],
                        skillPoints[1],
                        skillPoints[2],
                        skillPoints[3],
                        GameState.Difficulty.values()[difficultySpinner
                                .getSelectedItemPosition()]);
                transitionToGameState();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog confirmDialog = builder.create();

        createGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check that the player has entered a name.
                if (nameTextbox.getText().toString().length() == 0) {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.empty_name_notify, Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                // Check that the player has allocated all skill points.
                if (getTotalSkill() < Player.getSkillPointMax()) {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.skillpoints_notify, Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                confirmDialog.show();
            }
        });
    }

    void transitionToGameState() {
        Intent intent = new Intent(this, GameActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
        startActivity(intent);
        finish();
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
