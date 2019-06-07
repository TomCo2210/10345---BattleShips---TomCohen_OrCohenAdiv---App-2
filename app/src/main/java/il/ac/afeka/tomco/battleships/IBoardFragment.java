package il.ac.afeka.tomco.battleships;

import android.widget.GridView;

import il.ac.afeka.tomco.battleships.logic.Board;
import il.ac.afeka.tomco.battleships.logic.Ship;

public interface IBoardFragment {
    GridView getmGrid();
    Board getmBoard();
    Ship[] getShips();

}
