package edu.westga.tamikowilliamsattendanceapp.UIActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import edu.westga.tamikowilliamsattendanceapp.Database.DBHandler;
import edu.westga.tamikowilliamsattendanceapp.Database.Students;
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

        Students student = new Students();

        dbHandler.addStudent(student);
        studentLastBox.setText("");
        studentFirstBox.setText("");
    }
    public void lookupStudent (View view) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        Students student =
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
