package il.ac.afeka.tomco.battleships.highScoresBoard;

import java.util.ArrayList;

public class HighScoreContent {

    public static final ArrayList<HighScoreModel> HIGH_SCORES = new ArrayList<>();

    public static void clear() {
        HIGH_SCORES.clear();
    }

    public static void addHighScore(HighScoreModel highScore) {
        HIGH_SCORES.add(highScore);
    }
}
