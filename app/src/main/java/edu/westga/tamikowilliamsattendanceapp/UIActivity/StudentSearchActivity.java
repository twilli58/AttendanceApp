package edu.westga.tamikowilliamsattendanceapp.UIActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import edu.westga.tamikowilliamsattendanceapp.Database.DatabaseActivity;
import edu.westga.tamikowilliamsattendanceapp.R;
import edu.westga.tamikowilliamsattendanceapp.UIActivity.StudentListActivity;

public class StudentSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void click_StudentList(View view) {
        Intent intent = new Intent(view.getContext(), StudentListActivity.class);
        startActivity(intent);
    }
    public void click_Database(View view) {
        Intent intent = new Intent(view.getContext(), DatabaseActivity.class);
        startActivity(intent);
    }

}