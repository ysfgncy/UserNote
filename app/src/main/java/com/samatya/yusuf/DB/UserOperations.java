package com.samatya.yusuf.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.samatya.yusuf.models.User;

import java.util.ArrayList;
import java.util.List;

import static com.samatya.yusuf.DB.DbHandler.KEY_USERNAME;
import static com.samatya.yusuf.DB.DbHandler.KEY_USER_ID;
import static com.samatya.yusuf.DB.DbHandler.TABLE_USER;

/**
 * Created by yusuf on 5.10.2017.
 */

public class UserOperations {
    public static final String LOGTAG = "USER_MNGMNT_SYS";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            DbHandler.KEY_USER_ID,
            DbHandler.KEY_USERNAME,
            DbHandler.KEY_PASSWORD

    };

    public UserOperations(Context context){
        dbhandler = new DbHandler(context);
    }


    public void open(){
        database = dbhandler.getWritableDatabase();


    }
    public void close(){
        Log.i(LOGTAG, "Database Closed");
        dbhandler.close();

    }
    public User addUser(User user){
        ContentValues values  = new ContentValues();
        values.put(DbHandler.KEY_USERNAME,user.getUserName());
        values.put(DbHandler.KEY_PASSWORD, user.getPassWord());
        int insertId = (int) database.insert(TABLE_USER,null,values);
        user.setId(insertId);
        return user;

    }

    // Getting single Employee
    public User getUser(int id) {

        Cursor cursor = database.query(TABLE_USER,allColumns,DbHandler.KEY_USER_ID + "=?",new String[]{String.valueOf(id)},null,null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User e = new User(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
        // return Employee
        return e;
    }

    public List<User> getAllUser() {

        Cursor cursor = database.query(TABLE_USER,allColumns,null,null,null, null, null);

        List<User> users = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                User u = new User();
                u.setId(cursor.getColumnIndex(DbHandler.KEY_USER_ID));
                u.setUserName(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
                u.setPassWord(cursor.getString(cursor.getColumnIndex(DbHandler.KEY_PASSWORD)));
                users.add(u);
            }
        }
        // return All Users
        return users;
    }

    // Updating User
    public int updateUser(User user) {

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUserName());
        values.put(DbHandler.KEY_PASSWORD, user.getPassWord());


        // updating row
        return database.update(TABLE_USER, values,
                DbHandler.KEY_USER_ID + "=?",new String[] { String.valueOf(user.getId())});
    }

    // Deleting User
    public void removeUser(User user) {

        database.delete(TABLE_USER, DbHandler.KEY_USER_ID + "=" + user.getId(), null);
    }


    public boolean checkUser(String username) {

        // array of columns to fetch
        String[] columns = {
                KEY_USER_ID
        };

        database = dbhandler.getReadableDatabase();

        // selection criteria
        String selection = KEY_USERNAME + " = ?";

        // selection argument
        String[] selectionArgs = {username};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = database.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }


}
