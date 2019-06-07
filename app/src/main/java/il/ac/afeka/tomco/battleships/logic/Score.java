package il.ac.afeka.tomco.battleships.logic;

import android.widget.TextView;

import il.ac.afeka.tomco.battleships.R;

public class Score {

    private final int HIT_SCORE=100;
    private final int HIT_BY_PC_SCORE=-20;
    private final int MISS_SCORE=-10;
    public enum ScoreType {eHIT_SCORE,eHIT_BY_PC_SCORE,eMISS_SCORE};
    private final int SCORES[]={HIT_SCORE,HIT_BY_PC_SCORE,MISS_SCORE};

    private int currentGameScore =0;

    public Score() {
        this.currentGameScore = 0;
    }

    public int getCurrentGameScore() {
        return currentGameScore;
    }

    public void increaseGameScore(ScoreType scoreType) {
        this.currentGameScore += SCORES[scoreType.ordinal()];
    }
}
