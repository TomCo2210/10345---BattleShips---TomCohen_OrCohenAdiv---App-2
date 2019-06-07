package il.ac.afeka.tomco.battleships;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ButtonsFragment extends Fragment {

    interface onButtonTouchedListener {
        void onInstructionButtonClicked();
        void onNewGameButtonClicked();
        void onHighScoresButtonClicked();
    }

    private onButtonTouchedListener mListener;

    public ButtonsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buttons, container, false);
        Button b = (Button) view.findViewById(R.id.instructionsBtn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BUTTON", "INSTRUCTIONS BUTTON PRESSED");
                if (ButtonsFragment.this.mListener != null)
                    ButtonsFragment.this.mListener.onInstructionButtonClicked();
            }
        });
        b = (Button) view.findViewById(R.id.NewGameBtn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BUTTON", "HIGH SCORES BUTTON PRESSED");
                if (ButtonsFragment.this.mListener != null)
                    ButtonsFragment.this.mListener.onNewGameButtonClicked();
            }
        });

        b = (Button) view.findViewById(R.id.HighScoresBtn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BUTTON", "HIGH SCORES BUTTON PRESSED");
                if (ButtonsFragment.this.mListener != null)
                    ButtonsFragment.this.mListener.onHighScoresButtonClicked();
            }
        });
        return view;
    }

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
