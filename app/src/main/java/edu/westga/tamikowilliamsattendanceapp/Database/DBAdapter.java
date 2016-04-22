package edu.westga.tamikowilliamsattendanceapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Miko on 4/17/2016.
 * Modified by Miko on 4/22/2016.
 */
import edu.westga.tamikowilliamsattendanceapp.Database.DBContract.*;

public class DBAdapter {
    private static final String DB_NAME = "attendance";

    private static final String LOG_TAG = DBAdapter.class.getSimpleName();

    private static final int DB_VER = 1;

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context c) {
            super(c, DB_NAME, null, DB_VER);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + Student.TABLE_NAME + " ("
                    + Student._ID + " INTEGER PRIMARY KEY,"
                    + Student.FIRST_NAME + " TEXT NULL,"
                    + Student.LAST_NAME + " TEXT NULL,"
                    + Student.IS_ACTIVE + " INTEGER NULL"
                    + ");");
            db.execSQL("CREATE TABLE " + Course.TABLE_NAME + " ("
                    + Course._ID + " INTEGER PRIMARY KEY,"
                    + Course.NAME + " TEXT UNIQUE NOT NULL,"
                    + Course.IS_ACTIVE + " INTEGER NOT NULL"
                    + ");");
            db.execSQL("CREATE TABLE " + Enroll.TABLE_NAME + " ("
                    + Enroll._ID + " INTEGER PRIMARY KEY,"
                    + Enroll.STUDENT + " INTEGER NOT NULL,"
                    + Enroll.COURSE + " INTEGER NOT NULL,"
                    + Enroll.ACTIVE + " INTEGER NOT NULL"
                    + ");");
            db.execSQL("CREATE TABLE " + Attendance.TABLE_NAME + " ("
                    + Attendance._ID + " INTEGER PRIMARY KEY,"
                    + Attendance.DATE + " TEXT NOT NULL,"
                    + Attendance.ENROLL + " INTEGER NOT NULL,"
                    + ");");
        }

        public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
            db.execSQL("DROP TABLE IF EXISTS " + Student.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + Course.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + Enroll.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + Attendance.TABLE_NAME);
            onCreate(db);
        }
    }

    private final Context context;
    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public DBAdapter(Context c, Object o, Object o1, int i) {
        this.context = c;
    }

    public void close() {
        helper.close();
    }

    public void insertStudent(String firstName, String lastName) {
        Cursor cursor = db.rawQuery("SELECT " + Student._ID + " FROM " + Student.TABLE_NAME +
                        " WHERE " + Student.FIRST_NAME + "=? AND " + Student.LAST_NAME + "=?",
                new String[]{firstName, lastName});
        if(cursor.getCount() == 0) {
            ContentValues args = new ContentValues();
            args.put(Student.FIRST_NAME, firstName);
            args.put(Student.LAST_NAME, lastName);
            args.put(Student.IS_ACTIVE, 1);
            db.insert(Student.TABLE_NAME, null, args);
        }
        cursor.close();
    }

    public int getStudentId(String firstName, String lastName) {
        Cursor cursor = db.rawQuery("SELECT " + Student._ID + " FROM " + Student.TABLE_NAME +
                        " WHERE " + Student.FIRST_NAME + "=? AND " + Student.LAST_NAME + "=?",
                new String[]{firstName, lastName});
        int ret = -1;
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            ret = (int)cursor.getInt(0);
        }
        cursor.close();
        return ret;
    }

    public void editStudent(String firstName, String lastName, int id) {
        ContentValues args = new ContentValues();
        args.put(Student.FIRST_NAME, firstName);
        args.put(Student.LAST_NAME, lastName);
        db.update(Student.TABLE_NAME, args, Student._ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void removeStudent(int id) {
        ContentValues args = new ContentValues();
        args.put(Student.IS_ACTIVE, 0);
        db.update(Student.TABLE_NAME, args, Student._ID + " = ?",
                new String[]{String.valueOf(id)});
    }
}
