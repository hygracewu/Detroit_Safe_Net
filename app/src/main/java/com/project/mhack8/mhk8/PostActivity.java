package com.project.mhack8.mhk8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
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
    String name, departure, destination, date, time;
    List<DatabaseModel> dbList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbList= new ArrayList<DatabaseModel>();

        name="";
        departure="";
        destination="";
        date="";
        time="";

        etName = (TextView)findViewById(R.id.etName);
        etDeparture = (TextView)findViewById(R.id.etDeparture);
        etDestination =(TextView)findViewById(R.id.etDestination);
        etDate = (TextView)findViewById(R.id.etDate);
        etTime = (TextView)findViewById(R.id.etTime);

        etName.setText(name);
        etDeparture.setText(departure);
        etDestination.setText(destination);
        etDate.setText(date);
        etTime.setText(time);

        btngetdata =(Button)findViewById(R.id.btngetdata);
        btngetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PostActivity.this, ReserveActivity.class));
            }
        });

        btncan  =(Button)findViewById(R.id.btncan);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(PostActivity.this, ReserveActivity.class)); // back to map
            }

        });


        btnSubmit  =(Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                helpher = new DatabaseHelper(PostActivity.this);
                helpher.insertIntoDB(name, departure, destination, date, time);

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
