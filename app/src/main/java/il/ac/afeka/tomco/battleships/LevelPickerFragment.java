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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import il.ac.afeka.tomco.battleships.highScoresBoard.DataBaseHandler;


public class LevelPickerFragment extends Fragment {

    interface onButtonTouchedListener {
        void onEasyButtonClicked(View view);

        void onMediumButtonClicked(View view);

        void onHardButtonClicked(View view);

    }

    private TextView textView;
    private onButtonTouchedListener mListener;

    public LevelPickerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_level_picker, container, false);
        textView = view.findViewById(R.id.gameTitle);
        textView.setText(R.string.app_name);

        Button b = (Button) view.findViewById(R.id.EasyBtn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BUTTON", "EASY BUTTON PRESSED");
                if (LevelPickerFragment.this.mListener != null)
                    LevelPickerFragment.this.mListener.onEasyButtonClicked(view);
            }
        });
        b = (Button) view.findViewById(R.id.MediumBtn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BUTTON", "MEDIUM BUTTON PRESSED");
                if (LevelPickerFragment.this.mListener != null)
                    LevelPickerFragment.this.mListener.onMediumButtonClicked(view);
            }
        });
        b = (Button) view.findViewById(R.id.HardBtn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BUTTON", "HARD BUTTON PRESSED");
                if (LevelPickerFragment.this.mListener != null)
                    LevelPickerFragment.this.mListener.onHardButtonClicked(view);
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onButtonTouchedListener listener = (onButtonTouchedListener) context;
            if (listener != null)
                this.registerListener(listener);
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement onButtonTouchedListener");
        }
    }

    public void registerListener(onButtonTouchedListener listener) {
        this.mListener = listener;
    }

}
