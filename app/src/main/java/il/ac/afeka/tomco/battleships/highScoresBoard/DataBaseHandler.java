package il.ac.afeka.tomco.battleships.highScoresBoard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DataBaseHandler extends SQLiteOpenHelper {
    private static DataBaseHandler mInstance = null;
    private static final int DB_VERSION = 1;
    private static final String DATABASE_NAME = "HighScoresDB.db";

    //Tables:
    private static final String EASY_TABLE = "EASY";
    private static final String MEDIUM_TABLE = "MEDIUM";
    private static final String HARD_TABLE = "HARD";
    //Columns:
    private static final String KEY_USER_ID = "id";
    private static final String KEY_USER_NAME = "name";
    private static final String KEY_USER_SCORE = "score";
    //indices
    private static final int USER_ID_INDEX = 0;
    private static final int USER_NAME_INDEX = 1;
    private static final int USER_SCORE_INDEX = 2;

    private final static int MAX_RECORDS = 10;

    //Rows:
    private String[] level_row = {KEY_USER_ID, KEY_USER_NAME, KEY_USER_SCORE};

    //constructor:
    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }
    public static synchronized DataBaseHandler getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DataBaseHandler(context.getApplicationContext());
        }
        return mInstance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        createEasy(db);
        createMedium(db);
        createHard(db);
    }

    private void createEasy(SQLiteDatabase db) {
        String CREATE_EASY_TABLE = "CREATE TABLE " + EASY_TABLE + "("
                + KEY_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_USER_NAME + " TEXT,"
                + KEY_USER_SCORE + " INTEGER" + ")";
        db.execSQL(CREATE_EASY_TABLE);
        Log.d("Created EASY", "EASY TABLE Created");
    }

    private void createMedium(SQLiteDatabase db) {
        String CREATE_MEDIUM_TABLE = "CREATE TABLE " + MEDIUM_TABLE + "("
                + KEY_USER_ID + " TEXT PRIMARY KEY,"
                + KEY_USER_NAME + " TEXT,"
                + KEY_USER_SCORE + " INTEGER" + ")";
        db.execSQL(CREATE_MEDIUM_TABLE);
        Log.d("Created MEDIUM", "MEDIUM TABLE Created");
    }

    private void createHard(SQLiteDatabase db) {
        String CREATE_HARD_TABLE = "CREATE TABLE " + HARD_TABLE + "("
                + KEY_USER_ID + " TEXT PRIMARY KEY,"
                + KEY_USER_NAME + " TEXT,"
                + KEY_USER_SCORE + " INTEGER" + ")";
        db.execSQL(CREATE_HARD_TABLE);
        Log.d("Created HARD", "HARD TABLE Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + EASY_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MEDIUM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + HARD_TABLE);

        onCreate(db);
    }

    public void deleteTable(String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from" + tableName);
    }




    public void addWinnerToTable(String tableName, String playerName, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USER_NAME, playerName);
        contentValues.put(KEY_USER_SCORE, score);
        db.insert(tableName, null, contentValues);
    }

    public void loadHighScores(String tableName, int rowsCount) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sortBy = KEY_USER_SCORE + " DESC";

        Cursor cursor = db.query(
                tableName,
                level_row,
                null,
                null,
                null,
                null,
                sortBy,
                String.valueOf(rowsCount)
        );
        while (cursor.moveToNext()) {
            HighScoreModel highScoreModel = new HighScoreModel();
            highScoreModel.setID(cursor.getInt(cursor.getColumnIndexOrThrow(KEY_USER_ID)));
            highScoreModel.setName(cursor.getString(cursor.getColumnIndexOrThrow(KEY_USER_NAME)));
            highScoreModel.setPoints(cursor.getInt(cursor.getColumnIndexOrThrow(KEY_USER_SCORE)));
            HighScoreContent.addHighScore(highScoreModel);
        }
        cursor.close();
    }
    public void deleteLowestScore(String tableName) {
        SQLiteDatabase db = getWritableDatabase();

        String theQuery = "SELECT * FROM " + tableName + " ORDER BY " + KEY_USER_SCORE + " DESC;";

        Cursor c = db.rawQuery(theQuery, null);
        c.moveToLast();
        int id = c.getInt(USER_ID_INDEX);

        theQuery = "DELETE FROM " + tableName + " WHERE " + KEY_USER_ID + " = " + id + " ;";
        db.execSQL(theQuery);
    }
    public boolean isTableFull(String tableName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT count(*) FROM "+ tableName +";",null);
        c.moveToFirst();
        return c.getInt(0)==MAX_RECORDS;
    }
}
