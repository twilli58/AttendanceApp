package edu.westga.tamikowilliamsattendanceapp.UIActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import edu.westga.tamikowilliamsattendanceapp.R;

public class CourseStudentListActivity extends AppCompatActivity {
    ListView listView;
    // Define string array.
    private ListView listValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_student_list);

        this.populateCourseList();

    }
    private void populateCourseList() {
        listView = (ListView) findViewById(R.id.studentListView);
        List<String> students = new ArrayList<>();
        students.add("Crissy Miller");
        students.add("John Bass");
        students.add("Bill Murray");
        students.add("Cathy Cart");
        students.add("Brad Miller");
        students.add("Karen Riddle");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                students );

        listView.setAdapter(arrayAdapter);
    }


}
