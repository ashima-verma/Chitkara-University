package com.example.dell.chitkarauniversity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.dell.chitkarauniversity.data.ChitkaraContract;
import com.example.dell.chitkarauniversity.data.ChitkaraDbHelper;

public class EditActivity extends AppCompatActivity {

    public static final String TAG = EditActivity.class.getSimpleName();

    private EditText nameEdit;
    private EditText rollnoEdit;
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private EditText marksEdit;
    private String gender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        nameEdit = (EditText) findViewById(R.id.name_edit_text);
        rollnoEdit = (EditText) findViewById(R.id.rollno_edit_text);
        maleButton = (RadioButton) findViewById(R.id.male_radio_button);
        femaleButton = (RadioButton) findViewById(R.id.female_radio_button);
        marksEdit = (EditText) findViewById(R.id.marks_edit_text);


    }

    private void setGender() {
        if (maleButton.isChecked()) {
            Log.i(TAG, "Male Gender Selected");
            Toast.makeText(this, "Male Gender Selected", Toast.LENGTH_SHORT).show();
            gender = ChitkaraContract.ChitkaraStudent.MALE_GENDER;
        } else if (femaleButton.isChecked()) {
            Log.i(TAG, "Female Gender Selected");
            Toast.makeText(this, "Female Gender Selected", Toast.LENGTH_SHORT).show();
            gender = ChitkaraContract.ChitkaraStudent.FEMALE_GENDER;
        } else {
            Log.i(TAG, "Unknown Gender Selected");
            Toast.makeText(this, "Unknown Gender Selected", Toast.LENGTH_SHORT).show();
            gender = ChitkaraContract.ChitkaraStudent.UNKNOWN_GENDER;
        }

    }

    private void insertStudentData() {
        String studentName = nameEdit.getText().toString().trim();
        String studentRollno = rollnoEdit.getText().toString().trim();
        String studentMarks = marksEdit.getText().toString().trim();
        int marks = Integer.parseInt(studentMarks);


        ChitkaraDbHelper dbHelper = new ChitkaraDbHelper(this);
        SQLiteDatabase sdb = dbHelper.getWritableDatabase();


        ContentValues cv = new ContentValues();
        cv.put(ChitkaraContract.ChitkaraStudent.NAME_COLUMN, studentName);
        cv.put(ChitkaraContract.ChitkaraStudent.ROLLNO_COLUMN, studentRollno);
        cv.put(ChitkaraContract.ChitkaraStudent.GENDER_COLUMN, gender);
        cv.put(ChitkaraContract.ChitkaraStudent.MARKS_COLUMN, marks);

        long row_id = sdb.insert(ChitkaraContract.ChitkaraStudent.TABLE_NAME, null, cv);

        if (row_id == -1) {
            Toast.makeText(this, "Error in Saving Data", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Student data saved with row id: " + row_id, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_save:
                setGender();
                insertStudentData();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

