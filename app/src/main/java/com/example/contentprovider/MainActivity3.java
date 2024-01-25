package com.example.contentprovider;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {
    EditText title,id,author,year;
    Button delete, update;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //getting intent from second activity
        Intent bookDetails = getIntent();

        //finding the id of editText and button
        // assigning it to variables
        title=findViewById(R.id.nbookName);
        id=findViewById(R.id.nid);
        author=findViewById(R.id.nAuthor);
        year=findViewById(R.id.nyear);
        delete = findViewById(R.id.nDelete);
        update = findViewById(R.id.nUpdate);

        //setting the text of the editText from details given from second activity
        title.setText(bookDetails.getStringExtra("Title"));
        id.setText((bookDetails.getStringExtra("Id")));
        author.setText(bookDetails.getStringExtra("Author"));
        year.setText(bookDetails.getStringExtra("Year"));

        //storing the current book name
        String bookName = bookDetails.getStringExtra("Title");
        String p = bookDetails.getStringExtra("position");
        //creating a intent to send result to second activity
        Intent setResult = new Intent();

        //setting onClickListener for update button
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting the values from the editText and storing it as string
                String eTitle = title.getText().toString();
                String eId = id.getText().toString();
                String eAuthor = author.getText().toString();
                String eYear = year.getText().toString();

                //checking whether all fields are filled or not
                //if not then toast message will pop up
                if (eTitle.isEmpty() || eId.isEmpty() || eAuthor.isEmpty() || eYear.isEmpty()) {
                    android.widget.Toast.makeText(getApplicationContext(), "Please Enter All Fields", Toast.LENGTH_LONG).show();
                } else
                {
                    //setting the selection to title column
                    String select = "title =?";
                    //search for book name and update its details
                    String args[] = {bookName};
                    // class to add values in the database
                    ContentValues values = new ContentValues();

                    // fetching text from user
                    values.put(MyContentProvider.id,eId);
                    values.put(MyContentProvider.title,eTitle);
                    values.put(MyContentProvider.author,eAuthor);
                    values.put(MyContentProvider.year,eYear);

                    // updating a row in database through content URI
                    getContentResolver().update(MyContentProvider.CONTENT_URI, values,select,args);
                    // displaying a toast message
                    android.widget.Toast.makeText(getApplicationContext(), "Book details Updated", Toast.LENGTH_LONG).show();

                    //sending the details of book as result
                    setResult.putExtra("position",p);
                    setResult.putExtra("title",eTitle);
                    setResult.putExtra("id",eId);
                    setResult.putExtra("author",eAuthor);
                    setResult.putExtra("year",eYear);

                    //when successful completion of third activity it sends result to second activity
                    setResult(4,setResult);
                    //finishing the third activity
                    finish();
                }
            }
        });

        //setting onClickListener for delete button
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setting the selection to title column
                String select = "title =?";
                //search for book name and deletes its details
                String args[] = {bookName};
                // deleting a row in database through content URI
                getContentResolver().delete(MyContentProvider.CONTENT_URI,select,args);
                // displaying a toast message
                android.widget.Toast.makeText(getApplicationContext(), "Book details Deleted", Toast.LENGTH_LONG).show();
                setResult.putExtra("position",p);
                //when successful completion of third activity it sends result to second activity
                setResult(5,setResult);
                //finishing the third activity
                finish();
            }
        });
    }
}
