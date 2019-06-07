package il.ac.afeka.tomco.battleships;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;

import il.ac.afeka.tomco.battleships.logic.Board;
import il.ac.afeka.tomco.battleships.logic.Ship;


public class OpponentBoardFragment extends Fragment {
    private ProgressBar progressBar;
    private GridView mOpponentGrid;
    private Board mOpponentBoard;
    public Ship[] opponentShips = {new Ship(5, R.color.ship1),
            new Ship(4, R.color.ship2),
            new Ship(3, R.color.ship3),
            new Ship(3, R.color.ship4),
            new Ship(2, R.color.ship5)};

    public OpponentBoardFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_opponent_board, container, false);
        mOpponentGrid = view.findViewById(R.id.opponentBoardView);
        progressBar = view.findViewById(R.id.progressBar);
        //mOpponentGrid.setAdapter(new OpponentTileAdapter(getContext(), mOpponentBoard));
        return view;
    }

    public GridView getmGrid() {
        return mOpponentGrid;
    }

    public Board getmBoard() {
        return mOpponentBoard;
    }

    public Ship[] getShips() {
        return opponentShips;
    }




    public void setmOpponentBoard(Board mOpponentBoard) {
        this.mOpponentBoard = mOpponentBoard;
    }

    public void setProgressBarVisability(int state) {
        this.progressBar.setVisibility(state);
    }

    void setNumColumns(int numColumns) {
        mOpponentGrid.setNumColumns(numColumns);
    }
}
