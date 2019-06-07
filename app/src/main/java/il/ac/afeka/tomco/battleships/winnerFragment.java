package il.ac.afeka.tomco.battleships;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import il.ac.afeka.tomco.battleships.highScoresBoard.DataBaseHandler;


public class winnerFragment extends Fragment {
    final static String STRING_KEY = "STRING_KEY";

    private EditText text;
    private TextView textView;
    private DataBaseHandler mDBH;
    private Bundle bundle;
    Animation animBlink ;
    public winnerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_winner, container, false);
        textView = view.findViewById(R.id.winnerTitle);
        bundle = this.getArguments();
        if (bundle == null) {
            textView.setText(R.string.app_name);
        } else {
            String passedStrings[] = bundle.getString(STRING_KEY).split(" ");
            if ("PLAYER".equalsIgnoreCase(passedStrings[0])) {
                textView.setText("Player Wins!");
                mDBH = DataBaseHandler.getInstance(this.getActivity());
                if (mDBH.isTableFull(passedStrings[1]))
                    mDBH.deleteLowestScore(passedStrings[1]);
                createDialog();
            } else {
                textView.setText("Computer Wins!");
            }
        }
        animBlink = AnimationUtils.loadAnimation(view.getContext(),R.anim.blink);
        textView.startAnimation(animBlink);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    private void createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        text = new EditText(this.getContext());
        builder.setTitle(R.string.enter_name);
        builder.setView(text).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (text.getText().length() != 0) {
                    String passedStrings[] = bundle.getString(STRING_KEY).split(" ");
                    if (passedStrings[0] != null)
                        mDBH.addWinnerToTable(passedStrings[1], text.getText().toString(), Integer.parseInt(passedStrings[2]));
                }
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
