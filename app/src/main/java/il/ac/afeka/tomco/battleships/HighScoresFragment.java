package il.ac.afeka.tomco.battleships;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import il.ac.afeka.tomco.battleships.highScoresBoard.DataBaseHandler;
import il.ac.afeka.tomco.battleships.highScoresBoard.HighScoreAdapter;
import il.ac.afeka.tomco.battleships.highScoresBoard.HighScoreContent;


public class HighScoresFragment extends Fragment {

    final int NUM_OF_PLAYERS = 10;
    private DataBaseHandler mDBH;
    private String currentLevel = "EASY";
    private RecyclerView highScoresRecyclerView;

    public HighScoresFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDBH = DataBaseHandler.getInstance(this.getActivity());
    }

    private void loadHighScores() {
        HighScoreContent.clear();
        mDBH.loadHighScores(currentLevel,NUM_OF_PLAYERS);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_high_scores, container, false);
        mDBH = new DataBaseHandler(view.getContext());
        highScoresRecyclerView = (RecyclerView) view.findViewById(R.id.HighScoresRVList);
        highScoresRecyclerView.setHasFixedSize(true);
        highScoresRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadHighScores();
        highScoresRecyclerView.setAdapter(new HighScoreAdapter(HighScoreContent.HIGH_SCORES, this.getContext()));

        Button b =  view.findViewById(R.id.HSEasyBtn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BUTTON", "EASY BUTTON PRESSED");
                currentLevel  ="EASY";
                loadHighScores();
                highScoresRecyclerView.setAdapter(new HighScoreAdapter(HighScoreContent.HIGH_SCORES, getView().getContext()));
            }
        });
        b = view.findViewById(R.id.HSMediumBtn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BUTTON", "MEDIUM BUTTON PRESSED");
                currentLevel  ="MEDIUM";
                loadHighScores();
                highScoresRecyclerView.setAdapter(new HighScoreAdapter(HighScoreContent.HIGH_SCORES, getView().getContext()));
            }
        });
        b = view.findViewById(R.id.HSHardBtn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BUTTON", "HARD BUTTON PRESSED");
                currentLevel="HARD";
                loadHighScores();
                highScoresRecyclerView.setAdapter(new HighScoreAdapter(HighScoreContent.HIGH_SCORES, getView().getContext()));
            }
        });

        return view;
    }
}
