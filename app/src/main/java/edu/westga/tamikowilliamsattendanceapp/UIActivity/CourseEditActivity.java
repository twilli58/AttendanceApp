package edu.westga.tamikowilliamsattendanceapp.UIActivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import edu.westga.tamikowilliamsattendanceapp.R;

public class CourseEditActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_edit);
        this.populateCourseList();

    }
    private void populateCourseList() {
        listView = (ListView) findViewById(R.id.courseEditlistView);

        List<String> students = new ArrayList<>();
        students.add("Crissy Miller");
        students.add("John Bass");
        students.add("Bill Murray");
        students.add("Cathy Cart");
        students.add("Brad Miller");
        students.add("Karen Riddle");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,R.layout.single_row_course,
                R.id.textView8, students );

        listView.setAdapter(arrayAdapter);
    }
}
