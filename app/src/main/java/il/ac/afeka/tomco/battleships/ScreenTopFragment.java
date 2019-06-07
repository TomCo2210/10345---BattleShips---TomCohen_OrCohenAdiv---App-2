package il.ac.afeka.tomco.battleships;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import il.ac.afeka.tomco.battleships.logic.Board;
import il.ac.afeka.tomco.battleships.logic.Ship;


public class ScreenTopFragment extends Fragment implements IBoardFragment {
    private GridView mPlayerGrid;
    private Board mPlayerBoard;
    private TextView TurnTitleTV;
    private TextView PointsTV;
    public Ship[] playerShips = {new Ship(5, R.color.ship1),
            new Ship(4, R.color.ship2),
            new Ship(3, R.color.ship3),
            new Ship(3, R.color.ship4),
            new Ship(2, R.color.ship5)};

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen_top, container, false);
        mPlayerGrid = view.findViewById(R.id.opponentBoardView);
        TurnTitleTV = view.findViewById(R.id.TurnTitle);
        PointsTV = view.findViewById(R.id.Points);
        mPlayerGrid.setAdapter(new PlayerTileAdapter(getContext(), mPlayerBoard));
        return view;
    }
    @Override
    public GridView getmGrid() {
        return mPlayerGrid;
    }

    @Override
    public Board getmBoard() {
        return mPlayerBoard;
    }

    @Override
    public Ship[] getShips() {
        return playerShips;
    }

    public void setGridAdapter(PlayerTileAdapter playerTileAdapter) {
        mPlayerGrid.setAdapter(playerTileAdapter);
    }
    public ScreenTopFragment() {
    }

    public void setmPlayerBoard(Board mPlayerBoard) {
        this.mPlayerBoard = mPlayerBoard;
    }

    public TextView getTurnTitleTV() {
        return TurnTitleTV;
    }

    public TextView getPointsTV() {
        return PointsTV;
    }
    void setNumColumns(int numColumns)
    {
        mPlayerGrid.setNumColumns(numColumns);
    }
}
