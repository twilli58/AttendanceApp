package edu.westga.tamikowilliamsattendanceapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Miko on 4/17/2016.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "studentDB.db";
    public static final String TABLE_STUDENT = "student";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_STUDENTLASTNAME = "studentlast";
    public static final String COLUMN_STUDENTFIRSTNAME = "studentfirst";

    public DBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE " +
                TABLE_STUDENT + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_STUDENTLASTNAME
                + " TEXT," + COLUMN_STUDENTFIRSTNAME + " INTEGER" + ")";
        db.execSQL(CREATE_STUDENT_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);

    }
    public void addStudent(Student student) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENTLASTNAME, student.get_lastName());
        values.put(COLUMN_STUDENTFIRSTNAME, student.get_firstName());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_STUDENT, null, values);
        db.close();
    }
    public Student findStudent(String studentlast) {
        String query = "Select * FROM " + TABLE_STUDENT + " WHERE " + COLUMN_STUDENTLASTNAME + " =  \"" + studentlast + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Student student = new Student();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            student.set_id(Integer.parseInt(cursor.getString(0)));
            student.set_lastName(cursor.getString(1));
            student.set_firstName(cursor.getString(2));
            student.set_courseID(Integer.parseInt(cursor.getString(3)));
            cursor.close();
        } else {
            student = null;
        }
        db.close();
        return student;
    }
    public boolean deleteStudent(String studentlast) {

        boolean result = false;

        String query = "Select * FROM " + TABLE_STUDENT + " WHERE " + COLUMN_STUDENTLASTNAME + " =  \"" + studentlast + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Student student = new Student();

        if (cursor.moveToFirst()) {
            student.set_id(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_STUDENT, COLUMN_ID + " = ?",
                    new String[] { String.valueOf(student.get_id()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }


}
