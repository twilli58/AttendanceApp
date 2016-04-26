package edu.westga.tamikowilliamsattendanceapp.Model;

import android.content.Context;
import android.database.SQLException;

import edu.westga.tamikowilliamsattendanceapp.Database.DBAdapter;

/**
 * Created by Miko on 4/23/2016.
 */
public class Course {

    public static boolean addCourse(Context context, String name) {
        DBAdapter dba = new DBAdapter(context);
        boolean ret = false;
        try {
            dba.open();
            ret = dba.addCourse(name);
            dba.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static int findCourse(Context context, String name) {
        DBAdapter dba = new DBAdapter(context);
        int ret = -1;
        try {
            dba.open();
            ret = dba.getCourseId(name);
            dba.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }

    public static int countStudents(Context context, int id) {
        DBAdapter dba = new DBAdapter(context);
        int ret = 0;
        try {
            dba.open();
            ret = dba.countStudents(id);
            dba.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static void editCourse(Context context, String name, int id) {
        DBAdapter dba = new DBAdapter(context);
        try {
            dba.open();
            dba.editCourse(name, id);
            dba.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeCourse(Context context, int id) {
        DBAdapter dba = new DBAdapter(context);
        try {
            dba.open();
            dba.removeCourse(id);
            dba.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int addStudentInCourse(Context context, String firstName, String lastName, String course) {
        DBAdapter dba = new DBAdapter(context);
        try {
            dba.open();
            int student_id = dba.findStudent(firstName, lastName);
            if (student_id != -1) {
                int course_id = dba.getCourseId(course);
                if (course_id != -1) {
                    dba.enroll(student_id, course_id);
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

