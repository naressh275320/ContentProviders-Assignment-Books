package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    EditText name, id, desig, salary;
    Button load, upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.text0);
        id = findViewById(R.id.text1);
        desig = findViewById(R.id.text2);
        salary = findViewById(R.id.text3);
        upload = findViewById(R.id.b1);
        load = findViewById(R.id.b2);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eName = name.getText().toString();
                String eId = id.getText().toString();
                String eDesign = desig.getText().toString();
                String eSalary = salary.getText().toString();

                ContentValues values = new ContentValues();
                values.put(MyContentProvider.name, eName);
                values.put(MyContentProvider.id, eId);
                values.put(MyContentProvider.desig, eDesign);
                values.put(MyContentProvider.salary, eSalary);

                getContentResolver().insert(MyContentProvider.CONTENT_URI, values);

                android.widget.Toast.makeText(getApplicationContext(), "New Record Inserted", Toast.LENGTH_LONG).show();
            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {

                TextView resultView= (TextView) findViewById(R.id.res);

                Cursor cursor = getContentResolver().query(MyContentProvider.CONTENT_URI,
                        null, null, null, null);

                if(cursor.moveToFirst()) {
                    StringBuilder strBuild=new StringBuilder();
                    while (!cursor.isAfterLast()) {
                        strBuild.append("\n").
                                append(cursor.getString(cursor.getColumnIndex(MyContentProvider.name))).
                                append("-").append(cursor.getString(cursor.getColumnIndex(MyContentProvider.id))).
                                append("-").append(cursor.getString(cursor.getColumnIndex(MyContentProvider.desig))).
                                append("-").append(cursor.getString(cursor.getColumnIndex(MyContentProvider.salary)));
                        cursor.moveToNext();
                    }
                    resultView.setText(strBuild);
                }
                else {
                    resultView.setText("No Records Found");
                }
            }
        });
    }
}