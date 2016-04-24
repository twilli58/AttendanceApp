package edu.westga.tamikowilliamsattendanceapp.UIActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import edu.westga.tamikowilliamsattendanceapp.Model.Student;
import edu.westga.tamikowilliamsattendanceapp.R;

public class StudentActivity extends AppCompatActivity {
    TextView idView;
    EditText studentLastBox;
    EditText studentFirstBox;


    public StudentActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        String[] studentArray = {"charlie", "Betty", "Larry"};

        ArrayAdapter<String> myAdapter = new
                ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                studentArray);

    }

    public void newStudent (View view) {



    }
    public void lookupStudent (View view) {

    }

    public void editStudent (View view) {

    }
    public void removeStudent (View view) {

    }

}
