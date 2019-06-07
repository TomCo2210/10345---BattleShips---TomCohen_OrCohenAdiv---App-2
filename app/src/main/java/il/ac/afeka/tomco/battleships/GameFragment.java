package il.ac.afeka.tomco.battleships;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import il.ac.afeka.tomco.battleships.logic.Board;
import il.ac.afeka.tomco.battleships.logic.Game;
import il.ac.afeka.tomco.battleships.logic.Ship;
import il.ac.afeka.tomco.battleships.logic.Turn;

public class GameFragment extends Fragment {
    final static String STRING_KEY = "STRING_KEY";

    private Game mGame;

    private GridView mPlayerGrid;
    private Board mPlayerBoard;
    public Ship[] playerShips = {new Ship(5, R.color.ship1),
            new Ship(4, R.color.ship2),
            new Ship(3, R.color.ship3),
            new Ship(3, R.color.ship4),
            new Ship(2, R.color.ship5)};

    private GridView mOpponentGrid;
    private Board mOpponentBoard;
    public Ship[] opponentShips = {new Ship(5, R.color.ship1),
            new Ship(4, R.color.ship2),
            new Ship(3, R.color.ship3),
            new Ship(3, R.color.ship4),
            new Ship(2, R.color.ship5)};

    private TextView TurnTitleTV;
    private TextView PointsTV;
    private ProgressBar progressBar;
    private String passedString;
    public GameFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        mOpponentGrid = view.findViewById(R.id.opponentBoardView);
        progressBar = view.findViewById(R.id.progressBar);

        mPlayerGrid = view.findViewById(R.id.playerBoardView);
        TurnTitleTV = view.findViewById(R.id.TurnTitle);
        PointsTV = view.findViewById(R.id.Points);

        mGame = new Game();
        int boardSize = 0;
        Bundle b = this.getArguments();
        if (b != null) {
            passedString = b.getString(STRING_KEY);
            if (passedString != null) {
                switch (passedString) {
                    case "EASY":
                        boardSize = 25;
                        mOpponentGrid.setNumColumns(5);
                        mPlayerGrid.setNumColumns(5);
                        break;
                    case "MEDIUM":
                        boardSize = 64;
                        mOpponentGrid.setNumColumns(8);
                        mPlayerGrid.setNumColumns(8);
                        break;
                    case "HARD":
                        boardSize = 100;
                        mOpponentGrid.setNumColumns(10);
                        mPlayerGrid.setNumColumns(10);
                        break;
                }
            }

            mOpponentBoard = new Board(boardSize);
            mGame.setmOpponentBoard(mOpponentBoard);
            mOpponentGrid.setAdapter(new OpponentTileAdapter(getContext(), mOpponentBoard));
            mOpponentBoard.randomizeBoard(opponentShips);

            mPlayerBoard = new Board(boardSize);
            mGame.setmPlayerBoard(mPlayerBoard);

            mPlayerGrid.setAdapter(new PlayerTileAdapter(getContext(), mPlayerBoard));
            mPlayerBoard.randomizeBoard(playerShips);

            TurnTitleTV.setText(R.string.playersTurn);
            progressBar.setVisibility(View.INVISIBLE);
        }
        mOpponentGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (mGame.getmTurn() == Turn.PLAYER && mGame.playTile(mOpponentBoard, position)) {
                    ((OpponentTileAdapter) mOpponentGrid.getAdapter()).notifyDataSetChanged();
                    TurnTitleTV.setText(R.string.computerTurn);
                    PointsTV.setText(mGame.getmScore().getCurrentGameScore() + "Pts.");
                    progressBar.setVisibility(View.VISIBLE);
                    Turn winner = mGame.winCheck(mOpponentBoard);
                    mGame.toggleTurn();
                    if (winner == Turn.PLAYER) {
                        Fragment fragment = new winnerFragment();
                        Bundle b = new Bundle();
                        b.putString(STRING_KEY, winner.toString() + " " + passedString + " " + mGame.getmScore().getCurrentGameScore());
                        fragment.setArguments(b);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.MainFragmentSlot, fragment).addToBackStack(null).commit();
                    }
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            mGame.playComputer();
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ((PlayerTileAdapter) mPlayerGrid.getAdapter()).notifyDataSetChanged();
                                    TurnTitleTV.setText(R.string.playersTurn);
                                    PointsTV.setText(mGame.getmScore().getCurrentGameScore() + "Pts.");
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Turn winner = mGame.winCheck(mPlayerBoard);
                                    mGame.toggleTurn();
                                    if (winner == Turn.COMPUTER) {
                                        Fragment fragment = new winnerFragment();
                                        Bundle b = new Bundle();
                                        b.putString(STRING_KEY, winner.toString());
                                        fragment.setArguments(b);
                                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.MainFragmentSlot, fragment).addToBackStack(null).commit();
                                    }
                                }
                            });
                        }
                    });
                    t.start();

                }
            }
        });

        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }


}
