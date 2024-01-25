package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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

    EditText title,id,author,year;
    Button load,upload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title=findViewById(R.id.bookName);
        id=findViewById(R.id.id);
        author=findViewById(R.id.Author);
        year=findViewById(R.id.year);
        load=findViewById(R.id.Load);
        upload=findViewById(R.id.Upload);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eTitle = title.getText().toString();
                String eId = id.getText().toString();
                String eAuthor=author.getText().toString();
                String eYear = year.getText().toString();

                if(eTitle.isEmpty() || eId.isEmpty() || eAuthor.isEmpty() || eYear.isEmpty())
                {
                    android.widget.Toast.makeText(getApplicationContext(), "Please Enter All Fields", Toast.LENGTH_LONG).show();
                }
                else
                {
                    // class to add values in the database
                    ContentValues values = new ContentValues();

                    // fetching text from user
                    values.put(MyContentProvider.id,eId);
                    values.put(MyContentProvider.title,eTitle);
                    values.put(MyContentProvider.author,eAuthor);
                    values.put(MyContentProvider.year,eYear);

                    // inserting into database through content URI
                    getContentResolver().insert(MyContentProvider.CONTENT_URI, values);

                    // displaying a toast message
                    android.widget.Toast.makeText(getApplicationContext(), "New Record Inserted", Toast.LENGTH_LONG).show();

                    //resetting the edittext
                    title.setText("");
                    id.setText("");
                    author.setText("");
                    year.setText("");
                }
            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent up = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(up);
            }
        });
    }
}