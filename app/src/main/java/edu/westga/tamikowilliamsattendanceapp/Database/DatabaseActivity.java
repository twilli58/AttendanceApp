package edu.westga.tamikowilliamsattendanceapp.Database;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import edu.westga.tamikowilliamsattendanceapp.R;

public class DatabaseActivity extends AppCompatActivity {
    TextView idView;
    EditText studentLastBox;
    EditText studentFirstBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        idView = (TextView) findViewById(R.id.studentId);
        studentLastBox = (EditText) findViewById(R.id.lastEdit);
        studentFirstBox = (EditText) findViewById(R.id.firstEdit);

    }
    public void newStudent (View view) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        String last = studentLastBox.getText().toString();
        String first = studentFirstBox.getText().toString();

        Student student = new Student();

        dbHandler.addStudent(student);
        studentLastBox.setText("");
        studentFirstBox.setText("");
    }
    public void lookupStudent (View view) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        Student student =
                dbHandler.findStudent(studentLastBox.getText().toString());

        if (student != null) {
            idView.setText(String.valueOf(student.get_id()));

            studentFirstBox.setText(String.valueOf(student.get_firstName()));
        } else {
            idView.setText("No Match Found");
        }
    }

    public void removeStudent (View view) {
        DBHandler dbHandler = new DBHandler(this, null,
                null, 1);

        boolean result = dbHandler.deleteStudent(
                studentLastBox.getText().toString());

        if (result)
        {
            idView.setText("Record Deleted");
            studentLastBox.setText("");
            studentFirstBox.setText("");
        }
        else
            idView.setText("No Match Found");
    }

}
