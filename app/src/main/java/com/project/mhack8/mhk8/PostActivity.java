package com.project.mhack8.mhk8;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import database.DatabaseHelper;
import database.DatabaseModel;

/**
 * Created by eric on 2016/10/9.
 */
public class PostActivity extends CommonActivity{

    TextView etName,etDeparture,etDestination,etDate,etTime;
    Button btnSubmit,btngetdata,btncan;
    DatabaseHelper helpher;
    String name, departure, destination, date, time, responsed;
    List<DatabaseModel> dbList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        dbList= new ArrayList<DatabaseModel>();

        Intent intent = getIntent();
        String [] info = intent.getStringArrayExtra("info");
        Calendar c = Calendar.getInstance();

        etName = (EditText)findViewById(R.id.etName);
        etDeparture = (EditText)findViewById(R.id.etDeparture);
        etDestination =(EditText)findViewById(R.id.etDestination);
        etDate = (EditText)findViewById(R.id.etDate);
        etTime = (EditText)findViewById(R.id.etTime);

        btngetdata =(Button)findViewById(R.id.btngetdata);
        btngetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PostActivity.this, ReserveActivity.class));
            }
        });

        btnSubmit  =(Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                helpher = new DatabaseHelper(PostActivity.this);
                helpher.insertIntoDB(etName.getText().toString(), etDeparture.getText().toString(), etDestination.getText().toString(), etDate.getText().toString(), etTime.getText().toString());

                etName.setText("");
                etDeparture.setText("");
                etDestination.setText("");
                etDate.setText("");
                etTime.setText("");

                Toast.makeText(PostActivity.this, "Request Posted", Toast.LENGTH_LONG);
                // back to map
            }
        });

    }

}
