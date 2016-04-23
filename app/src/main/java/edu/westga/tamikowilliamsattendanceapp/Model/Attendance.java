package edu.westga.tamikowilliamsattendanceapp.Model;

import android.content.Context;
import android.database.SQLException;

import java.util.Date;

import edu.westga.tamikowilliamsattendanceapp.Database.DBAdapter;

/**
 * Created by Miko on 4/18/2016.
 * Changing as of 4/22/2016.
 */
public class Attendance {

    public static String DATE;
    public static String _ID;
    public static String ENROLL;
    public static String TABLE_NAME;

    public static int markAttendance(Context context, String firstName, String lastName, String course, String date) {
        DBAdapter dba = new DBAdapter(context);
        try {
            dba.open();
            int student_id = dba.getStudentId(firstName, lastName);
            if (student_id != -1) {
                int course_id = dba.getCourseId(course);
                if (course_id != -1) {
                    dba.markAttendance(date, student_id, course_id);
                    return 0;
                }
            }
            dba.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}

