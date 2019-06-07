package il.ac.afeka.tomco.battleships.highScoresBoard;

import android.os.Parcel;
import android.os.Parcelable;

public class HighScoreModel implements Parcelable {

    private int ID;
    private String name;
    private int points;

    public HighScoreModel() {
    }

    protected HighScoreModel(Parcel in) {
        this.name = in.readString();
        this.points = in.readInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoints() {
        return "" + points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getID() {
        return "" + ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "HighScoreModel{" +
                "name='" + name + '\'' +
                ", points=" + points +
                '}';
    }

    public static final Creator<HighScoreModel> CREATOR = new Creator<HighScoreModel>() {
        @Override
        public HighScoreModel createFromParcel(Parcel in) {
            return new HighScoreModel(in);
        }

        @Override
        public HighScoreModel[] newArray(int size) {
            return new HighScoreModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(points);
    }
}
