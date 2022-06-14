package com.example.indoorgps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class location<DBHandler> extends AppCompatActivity {


    DatabaseHelper myDB;
    Button btnAdd,btndelete,btnView;
    EditText editText;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        editText = (EditText) findViewById(R.id.enterlocation);
        btnAdd = (Button) findViewById(R.id.addlocation);
        btndelete=(Button) findViewById(R.id.deletelocation);
        btnView = (Button) findViewById(R.id.viewlocation);
        myDB = new DatabaseHelper(this);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                if(editText.length()!= 0){
                    AddData(newEntry);
                    editText.setText("");
                }else{
                    Toast.makeText(location.this, "You must put something in the text field!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                       /* int deletedRows = myDB.deletelocation(editText.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(location.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(location.this,"Data not Deleted",Toast.LENGTH_LONG).show();*/


                myDB.deletelocation(editText.getText().toString());
                Toast.makeText(location.this, "Deleted the location", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(location.this, ViewListContents.class);
                startActivity(i);
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(location.this, ViewListContents.class);
                startActivity(intent);
            }
        });


    }

    public void AddData(String newEntry) {

        boolean insertData = myDB.addData(newEntry);

        if(insertData==true){
            Toast.makeText(this, "Data Successfully Inserted!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
        }
    }



}