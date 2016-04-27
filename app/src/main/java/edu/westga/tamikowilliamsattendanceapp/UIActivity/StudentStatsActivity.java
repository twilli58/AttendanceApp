package edu.westga.tamikowilliamsattendanceapp.UIActivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.westga.tamikowilliamsattendanceapp.R;

public class StudentStatsActivity extends AppCompatActivity {
    Spinner spinner;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_stats);
        spinner = (Spinner) findViewById(R.id.spinnerStudentStats);
        //this.populateStudentStats();
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.month_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " selected", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
/*
    private void populateStudentStats() {
            listView = (ListView) findViewById(R.id.studentStatsListView);

            List<String> studentStats = new ArrayList<>();
            studentStats.add("Crissy Miller");
            studentStats.add("John Bass");
            studentStats.add("Bill Murray");
            studentStats.add("Cathy Cart");
            studentStats.add("Brad Miller");
            studentStats.add("Karen Riddle");

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    this,R.layout.multi_row_stats,
                    R.id.studentStatseditText, studentStats );

            listView.setAdapter(arrayAdapter);
        }*/
    }


