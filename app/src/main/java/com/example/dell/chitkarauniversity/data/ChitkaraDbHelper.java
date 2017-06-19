package com.example.dell.chitkarauniversity.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.example.dell.chitkarauniversity.data.ChitkaraContract.ChitkaraStudent;

/**
 * Created by dell on 4/23/2017.
 */

public class ChitkaraDbHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION =1;

    public static final String DB_NAME = "students.db";

    public ChitkaraDbHelper(Context c){
        super(c,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sdb){
        String SQL_CREATE_TABLE = "CREATE TABLE " + ChitkaraStudent.TABLE_NAME + " (" + ChitkaraStudent._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ChitkaraStudent.NAME_COLUMN + " TEXT NOT NULL, " + ChitkaraStudent.ROLLNO_COLUMN + " TEXT NOT NULL, " + ChitkaraStudent.GENDER_COLUMN + " TEXT NOT NULL, " + ChitkaraStudent.MARKS_COLUMN + " INTEGER NOT NULL DEFAULT 0);";
        sdb.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sdb, int oldVersion, int newVersion){

    }
}
