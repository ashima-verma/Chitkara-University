package com.example.dell.chitkarauniversity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.dell.chitkarauniversity.data.ChitkaraContract.ChitkaraStudent;
import com.example.dell.chitkarauniversity.data.ChitkaraDbHelper;

public class CatalogActivity extends AppCompatActivity {

    private ChitkaraDbHelper dbHelper;

    private String count;

    SQLiteDatabase data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        FloatingActionButton editButton = (FloatingActionButton) findViewById(R.id.floating_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(CatalogActivity.this, EditActivity.class);
                startActivity(editIntent);
            }
        });

        dbHelper = new ChitkaraDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabase();
    }


    private void displayDatabase() {
        data = dbHelper.getReadableDatabase();

        Cursor cursor = rCursor();

        try {
            TextView displayText = (TextView) findViewById(R.id.display_text);
            count = "No of rows in database is: " + cursor.getCount();
            displayText.setText(count);

            displayText.setText("\nNo of students in database is " + cursor.getCount() + "\n\n");
            displayText.append(ChitkaraStudent._ID + "  -  " + ChitkaraStudent.NAME_COLUMN + "  -  " + ChitkaraStudent.ROLLNO_COLUMN + "  -  " + ChitkaraStudent.GENDER_COLUMN + " -  " + ChitkaraStudent.MARKS_COLUMN);

            int idIndex = cursor.getColumnIndex(ChitkaraStudent._ID);
            int nameIndex = cursor.getColumnIndex(ChitkaraStudent.NAME_COLUMN);
            int rollnoIndex = cursor.getColumnIndex(ChitkaraStudent.ROLLNO_COLUMN);
            int genderIndex = cursor.getColumnIndex(ChitkaraStudent.GENDER_COLUMN);
            int marksIndex = cursor.getColumnIndex(ChitkaraStudent.MARKS_COLUMN);

            while (cursor.moveToNext()) {
                int id = cursor.getInt(idIndex);
                String name = cursor.getString(nameIndex);
                String rollno = cursor.getString(rollnoIndex);
                String gender = cursor.getString(genderIndex);
                int marks = cursor.getInt(marksIndex);

                displayText.append("\n" + id + " - " + name + " - " + rollno + " - " + gender + " - " + marks);
            }
        } finally {
            cursor.close();
        }
    }

    public Cursor rCursor() {
        String[] projection = {ChitkaraStudent._ID, ChitkaraStudent.NAME_COLUMN, ChitkaraStudent.ROLLNO_COLUMN, ChitkaraStudent.GENDER_COLUMN, ChitkaraStudent.MARKS_COLUMN};
        Cursor c = data.query(ChitkaraStudent.TABLE_NAME, projection, null, null, null, null, null);
        return c;
    }

}
