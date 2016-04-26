package edu.westga.tamikowilliamsattendanceapp;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;

import edu.westga.tamikowilliamsattendanceapp.Database.DBAdapter;
import edu.westga.tamikowilliamsattendanceapp.Model.Student;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class StudentUnitTest {
    private static Context context;
    @Before
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void addsAStudent() throws Exception {
        Student s = new Student();
        s.setFirstName("Betty ");
        s.setLastName("Greggor");
        String results = (s.getFirstName().toString() + s.getLastName().toString());
        assertEquals("Betty Greggor", results);
    }


}