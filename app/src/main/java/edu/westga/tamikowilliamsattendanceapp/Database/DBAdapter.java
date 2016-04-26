package edu.westga.tamikowilliamsattendanceapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import edu.westga.tamikowilliamsattendanceapp.Database.DBContract.*;

/**
 * Created by Miko on 4/17/2016.
 * Modified by Miko on 4/22/2016.
 */

public class DBAdapter {
    private static final String DB_NAME = "roll_call";

    private static final String LOG_TAG = DBAdapter.class.getSimpleName();

    private static final int DB_VER = 1;
    private static Context context;
    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public DBAdapter(Context c) {
        this.context = c;
    }

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
                    + Attendance.PRESENT + " INTEGER NOT NULL,"
                    + Attendance.ENROLL + " INTEGER NOT NULL,"
                    + Attendance.ACTIVE + " INTEGER NOT NULL"
                    + ");");
        }

        public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
            db.execSQL("DROP TABLE IF EXISTS " + Student.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + Course.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + Enroll.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + edu.westga.tamikowilliamsattendanceapp.Model.Attendance.TABLE_NAME);
            onCreate(db);
        }
    }

    public DBAdapter open() throws SQLException {
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
        return this;
    }

    public void close() {
        helper.close();
    }

    public boolean addStudent(String firstName, String lastName) {
        Cursor cursor = db.rawQuery("SELECT " + Student._ID + " FROM " + Student.TABLE_NAME +
                        " WHERE " + Student.FIRST_NAME + "=? AND " + Student.LAST_NAME + "=?",
                new String[]{firstName, lastName});
        boolean ret = false;
        if (cursor.getCount() == 0) {
            ContentValues args = new ContentValues();
            args.put(Student.FIRST_NAME, firstName);
            args.put(Student.LAST_NAME, lastName);
            args.put(Student.IS_ACTIVE, 1);
            db.insert(Student.TABLE_NAME, null, args);
            ret = true;
        }
        cursor.close();

        cursor = db.rawQuery("SELECT " + Student._ID + " FROM " + Student.TABLE_NAME
                        + " WHERE " + Student.FIRST_NAME + "=? AND " + Student.LAST_NAME
                        + "=? AND " + Student.IS_ACTIVE + "=0",
                new String[]{firstName, lastName});
        if (cursor.getCount() == 1) {
            ContentValues args = new ContentValues();
            args.put(Student.FIRST_NAME, firstName);
            args.put(Student.LAST_NAME, lastName);
            args.put(Student.IS_ACTIVE, 1);
            db.update(Student.TABLE_NAME, args, "WHERE " + Student._ID + "=?",
                    new String[] {String.valueOf(cursor.getLong(0))});
            ret = true;
        }
        return ret;
    }

    public int findStudent(String firstName, String lastName) {
        Cursor cursor = db.rawQuery("SELECT " + Student._ID + " FROM " + Student.TABLE_NAME +
                        " WHERE " + Student.FIRST_NAME + "=? AND " + Student.LAST_NAME + "=? AND "
                        + Student.IS_ACTIVE + "=1",
                new String[]{firstName, lastName});
        int ret = -1;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            ret = (int) cursor.getInt(0);
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

    public boolean addCourse(String name) {
        Cursor cursor = db.rawQuery("SELECT " + Course._ID + " FROM " + Course.TABLE_NAME +
                        " WHERE " + Course.NAME + "=? ",
                new String[]{name});
        boolean ret = false;
        if (cursor.getCount() == 0) {
            ContentValues args = new ContentValues();
            args.put(Course.NAME, name);
            args.put(Course.IS_ACTIVE, 1);
            db.insert(Course.TABLE_NAME, null, args);
            ret = true;
        }
        cursor.close();

        cursor = db.rawQuery("SELECT " + Course._ID + " FROM " + Course.TABLE_NAME
                        + " WHERE " + Course.NAME + "=? AND " + Course.IS_ACTIVE + "=0",
                new String[]{name});
        if (cursor.getCount() == 1) {
            ContentValues args = new ContentValues();
            args.put(Course.NAME, name);
            args.put(Course.IS_ACTIVE, 1);
            db.update(Course.TABLE_NAME, args, "WHERE " + Course._ID + "=?",
                    new String[] {String.valueOf(cursor.getLong(0))});
            ret = true;
        }

        return ret;
    }

    public int getCourseId(String name) {
        Cursor cursor = db.rawQuery("SELECT " + Course._ID + " FROM " + Course.TABLE_NAME +
                        " WHERE " + Course.NAME + "=? AND " + Course.IS_ACTIVE + "=1",
                new String[]{name});
        int ret = -1;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            ret = (int) cursor.getInt(0);
        }
        cursor.close();
        return ret;
    }

    public int countStudents(int id) {
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + Enroll.TABLE_NAME +
                        " WHERE " + Enroll.COURSE + "=? AND " + Enroll.ACTIVE + "=1",
                new String[]{String.valueOf(id)});
        int ret = 0;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            ret = (int) cursor.getInt(0);
        }
        cursor.close();
        return ret;
    }

    public void editCourse(String name, int id) {
        ContentValues args = new ContentValues();
        args.put(Course.NAME, name);
        db.update(Course.TABLE_NAME, args, Course._ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void removeCourse(int id) {
        ContentValues args = new ContentValues();
        args.put(Course.IS_ACTIVE, 0);
        db.update(Course.TABLE_NAME, args, Course._ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void enroll(int student_id, int course_id) {
        Cursor cursor = db.rawQuery("SELECT " + Enroll._ID + " FROM " + Enroll.TABLE_NAME +
                        " WHERE " + Enroll.STUDENT + "=? AND " + Enroll.COURSE + "=?",
                new String[]{String.valueOf(student_id), String.valueOf(course_id)});
        if (cursor.getCount() == 0) {
            ContentValues args = new ContentValues();
            args.put(Enroll.STUDENT, student_id);
            args.put(Enroll.COURSE, course_id);
            args.put(Enroll.ACTIVE, 1);
            db.insert(Enroll.TABLE_NAME, null, args);
        }
        cursor.close();
    }

    public void markAttendance(String date, int present, int enroll) {
        Cursor cursor = db.rawQuery("SELECT " + Attendance._ID + " FROM " + Attendance.TABLE_NAME +
                        " WHERE " + Attendance.ENROLL + "=? AND " + Attendance.DATE + "=?",
                new String[]{String.valueOf(enroll), String.valueOf(date)});
        if (cursor.getCount() == 0) {
            ContentValues args = new ContentValues();
            args.put(Attendance.DATE, date);
            args.put(Attendance.ENROLL, enroll);
            args.put(Attendance.PRESENT, present);
            db.insert(Attendance.TABLE_NAME, null, args);
        }
        cursor.close();
    }
}
