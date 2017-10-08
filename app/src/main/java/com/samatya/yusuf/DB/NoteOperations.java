package com.samatya.yusuf.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.samatya.yusuf.models.Notes;

import java.util.ArrayList;
import java.util.HashMap;

import static com.samatya.yusuf.DB.DbHandler.TABLE_NOTES;

/**
 * Created by yusuf on 5.10.2017.
 */

public class NoteOperations {
    public static final String LOGTAG = "NOTE_MNGMNT_SYS";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allNotes = {
            DbHandler.KEY_NOTE_ID,
            DbHandler.KEY_NOTE_USER_ID,
            DbHandler.KEY_TITLE,
            DbHandler.KEY_CONTENT

    };

    public NoteOperations(Context context){
        dbhandler = new DbHandler(context);
    }

    public void open(){
        Log.i(LOGTAG,"Database Opened");
        database = dbhandler.getWritableDatabase();


    }
    public void close(){
        Log.i(LOGTAG, "Database Closed");
        dbhandler.close();

    }
    public Notes addNote(Notes note){
        ContentValues values  = new ContentValues();
        values.put(DbHandler.KEY_NOTE_ID,note.getId());
        values.put(DbHandler.KEY_NOTE_USER_ID, note.getNote_user_id());
        values.put(DbHandler.KEY_TITLE, note.getTitle());
        values.put(DbHandler.KEY_CONTENT, note.getContent());
        int insertId = (int) database.insert(DbHandler.TABLE_NOTES,null,values);
        note.setId(insertId);
        return note;
    }

    // Getting single Note
    public Notes getNote(int id) {

        Cursor cursor = database.query(TABLE_NOTES,allNotes,DbHandler.KEY_NOTE_ID + "=?",new String[]{String.valueOf(id)},null,null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Notes n = new Notes(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getString(3));
        // return Note
        return n;
    }



    public ArrayList<HashMap<String, String>>  getAllNotes(int note_user_id) {
        //Open connection to read only
        SQLiteDatabase db = dbhandler.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                DbHandler.KEY_TITLE + "," +
                DbHandler.KEY_CONTENT +
                " FROM " + DbHandler.TABLE_NOTES
                + " WHERE " +
                DbHandler.KEY_NOTE_USER_ID + "=?";

        //Student student = new Student();
        ArrayList<HashMap<String, String>> noteList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(note_user_id)});
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                HashMap<String, String> note = new HashMap<String, String>();
                note.put("title", cursor.getString(cursor.getColumnIndex(DbHandler.KEY_TITLE)));
                note.put("content", cursor.getString(cursor.getColumnIndex(DbHandler.KEY_CONTENT)));
                noteList.add(note);
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();
        return noteList;

    }




    // Updating Note
    public int updateNote(Notes note) {

        ContentValues values = new ContentValues();
        values.put(DbHandler.KEY_TITLE, note.getTitle());
        values.put(DbHandler.KEY_CONTENT, note.getContent());

        // updating row
        return database.update(TABLE_NOTES, values,
                DbHandler.KEY_NOTE_ID + "=?",new String[] { String.valueOf(note.getId())});
    }

    // Deleting Note
    public void removeNote(Notes note) {
        database.delete(TABLE_NOTES, DbHandler.KEY_NOTE_ID + "=" + note.getId(), null);
    }





}
