package com.funmilola.mynoteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DatabaseHelper mDatabaseHelper;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    public DBManager (Context c){
        mContext = c;
    }
    public  DBManager open() throws SQLException {
      mDatabaseHelper =  new DatabaseHelper(mContext);
        mDatabase = mDatabaseHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        mDatabaseHelper.close();
    }
    public void insert (String name, String desc){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.SUBJECT, name);
        contentValues.put(DatabaseHelper.DESC, desc);
        mDatabase.insert(DatabaseHelper.TABLE_NAME,
               null,
                contentValues);
    }
    public Cursor fetch(){
        String[] columns = new String[]{DatabaseHelper._ID,
        DatabaseHelper.SUBJECT,
        DatabaseHelper.DESC};
        Cursor cursor = mDatabase.query(DatabaseHelper.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null);
        if (cursor!= null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public int update (long _id, String name, String desc ){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.SUBJECT, name);
        contentValues.put(DatabaseHelper.DESC, desc);
        int i = mDatabase.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID
        + " = "
        + _id,
                null);
        return i;

    }
    public void delete (long _id){
        mDatabase.delete(DatabaseHelper.TABLE_NAME,
                DatabaseHelper._ID
        + "="
        + _id,
                null);
    }
}
