package edu.westga.tamikowilliamsattendanceapp.Database;

import java.util.Date;

/**
 * Created by Miko on 4/18/2016.
 * Changing as of 4/22/2016.
 */
public class Attendance {

    public static String TABLE_NAME;
    public static String _ID;
    public static String DATE;
    public static String ENROLL;
    private int _id;
    private Date _date;
    private int _studentID;
    private String _present;
    private String _absent;
    private String _late;

   public Attendance(int id, Date date, int studentID, String present, String absent, String late) {
        this._id = id;
        this._date = date;
        this._studentID = studentID;
        this._present = present;
        this._absent = absent;
        this._late = late;
    }

    public Attendance(String name, Date date, int studentID, String present, String absent, String late) {
        this._date = date;
        this._studentID = studentID;
        this._present = present;
        this._absent = absent;
        this._late = late;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public Date get_date() {
        return _date;
    }

    public void set_date(Date _date) {
        this._date = _date;
    }

    public int get_studentID() {
        return _studentID;
    }

    public void set_studentID(int _studentID) {
        this._studentID = _studentID;
    }

    public String get_present() {
        return _present;
    }

    public void set_present(String _present) {
        this._present = _present;
    }

    public String get_absent() {
        return _absent;
    }

    public void set_absent(String _absent) {
        this._absent = _absent;
    }

    public String get_late() {
        return _late;
    }

    public void set_late(String _late) {
        this._late = _late;
    }
}
