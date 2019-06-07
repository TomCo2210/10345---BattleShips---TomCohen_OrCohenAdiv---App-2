package il.ac.afeka.tomco.battleships;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements ButtonsFragment.onButtonTouchedListener,
        LevelPickerFragment.onButtonTouchedListener {
    final static String STRING_KEY = "STRING_KEY";

    Fragment mainFragment;
    ButtonsFragment buttonsFragment;

    //TODO: Use Fragments! All other screens are fragments!
    //TODO: SQLite: 10 high scores board for each level, new high score requests for name.
    //TODO: service: bound service for checking the orientation sensors.
    //TODO: sensors: 5 seconds of right or left rotation will move the ships toward that border.
    //TODO: Animations: HIT/MISS, Winner Banner. at least two kinds of animations.
    //TODO: Logic Design, WireFrame, Working App!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainFragment = new LevelPickerFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.MainFragmentSlot, mainFragment).commit();
        buttonsFragment = new ButtonsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.ButtonsFragmentSlot, buttonsFragment).commit();
    }


    public void onEasyButtonClicked(View view) {
        Fragment gameFragment = new GameFragment();
        Bundle b = new Bundle();
        b.putString(STRING_KEY, "EASY");
        gameFragment.setArguments(b);
        getSupportFragmentManager().beginTransaction().replace(R.id.MainFragmentSlot, gameFragment).addToBackStack(null).commit();
    }

    public void onMediumButtonClicked(View view) {
        Fragment gameFragment = new GameFragment();
        Bundle b = new Bundle();
        b.putString(STRING_KEY, "MEDIUM");
        gameFragment.setArguments(b);
        getSupportFragmentManager().beginTransaction().replace(R.id.MainFragmentSlot, gameFragment).addToBackStack(null).commit();
    }

    public void onHardButtonClicked(View view) {
        Fragment gameFragment = new GameFragment();
        Bundle b = new Bundle();
        b.putString(STRING_KEY, "HARD");
        gameFragment.setArguments(b);
        getSupportFragmentManager().beginTransaction().replace(R.id.MainFragmentSlot, gameFragment).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0)
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Exiting Game")
                    .setMessage("Are you sure you want to Exit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        else
            super.onBackPressed();
    }

    @Override
    public void onInstructionButtonClicked() {
        getSupportFragmentManager().beginTransaction().replace(R.id.MainFragmentSlot, new InstructionsFragment()).addToBackStack(null).commit();
    }

    @Override
    public void onNewGameButtonClicked() {
        getSupportFragmentManager().beginTransaction().replace(R.id.MainFragmentSlot, new LevelPickerFragment()).commit();
    }

    @Override
    public void onHighScoresButtonClicked() {
        getSupportFragmentManager().beginTransaction().replace(R.id.MainFragmentSlot, new HighScoresFragment()).addToBackStack(null).commit();
    }

}
