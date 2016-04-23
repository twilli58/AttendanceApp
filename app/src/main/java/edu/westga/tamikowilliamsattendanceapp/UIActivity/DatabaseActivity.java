package edu.westga.tamikowilliamsattendanceapp.UIActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import edu.westga.tamikowilliamsattendanceapp.Database.DBAdapter;
import edu.westga.tamikowilliamsattendanceapp.Database.DBContract;
import edu.westga.tamikowilliamsattendanceapp.Model.Student;
import edu.westga.tamikowilliamsattendanceapp.R;

public class DatabaseActivity extends AppCompatActivity {
    TextView idView;
    EditText studentLastBox;
    EditText studentFirstBox;
    String firstName;
    String lastName;
    int studentID;

    public DatabaseActivity() {
        int studentID = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        idView = (TextView) findViewById(R.id.studentId);
        studentLastBox = (EditText) findViewById(R.id.lastEdit);
        studentFirstBox = (EditText) findViewById(R.id.firstEdit);
        DBContract.Student student = new DBContract.Student();
    }
    public void newStudent (View view) {
        DBAdapter dbAdapter = new DBAdapter(this);

        dbAdapter.addStudent(firstName, lastName);
        studentLastBox.setText("");
        studentFirstBox.setText("");
    }
    public void lookupStudent (View view) {
        /*DBAdapter dbAdapter = new DBAdapter(this);

        Student studentID = dbAdapter.findStudent();

        if (studentID != null) {
            idView.setText(String.valueOf(studentID.toString()));

            studentFirstBox.setText(String.valueOf(studentID.toString()));
        } else {
            idView.setText("No Match Found");
        }*/
    }

    public void edit (View view) {

    }
    public void removeStudent (View view) {
       /*DBAdapter dbAdapter = new DBAdapter(this);

        boolean result = dbAdapter.removeStudent(id);

        if (result)
        {
            idView.setText("Record Deleted");
            studentLastBox.setText("");
            studentFirstBox.setText("");
        }
        else
            idView.setText("No Match Found");*/
    }
    public void editStudent() {

    }

}
