package com.samatya.yusuf.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "notesManager";

    public static final String TABLE_USER = "user";
    public static final String TABLE_NOTES = "notes";

    public static final String KEY_USER_ID = "userId";
    public static final String KEY_USERNAME = "userName";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_NOTE_ID = "noteId";
    public static final String KEY_NOTE_USER_ID = "noteUserId";
    public static final String KEY_TITLE = "title";
    public static final String KEY_CONTENT = "content";

    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "("
            + KEY_USER_ID + " INTEGER PRIMARY KEY, "
            + KEY_USERNAME + " TEXT, "
            + KEY_PASSWORD + " TEXT"
            + ")";

    private static final String CREATE_TABLE_NOTES = "CREATE TABLE "
            + TABLE_NOTES + "("
            + KEY_NOTE_ID + " INTEGER PRIMARY KEY, "
            + KEY_NOTE_USER_ID + " INTEGER, "
            + KEY_TITLE + " TEXT, "
            + KEY_CONTENT + " TEXT"
            + ")";


    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_NOTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);

        // create new tables
        onCreate(db);
    }


}
