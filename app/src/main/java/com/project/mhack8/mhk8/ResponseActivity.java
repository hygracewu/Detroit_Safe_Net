package com.project.mhack8.mhk8;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import database.DatabaseHelper;
import database.DatabaseModel;

/**
 * Created by eric on 2016/10/9.
 */

public class ResponseActivity extends CommonActivity {

    DatabaseHelper helpher;
    List<DatabaseModel> dbList;
    int position;
    TextView tvname,tvdeparture,tvdestination,tvdate,tvtime;
    Button btnConfirm,btnCancel;
    private int prevPageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        position = bundle.getInt("position");

        tvname = ((TextView)findViewById(R.id.name));
        tvdeparture =(TextView)findViewById(R.id.departure);
        tvdestination =(TextView)findViewById(R.id.destination);
        tvdate =(TextView)findViewById(R.id.date);
        tvtime =(TextView)findViewById(R.id.time);


        helpher = new DatabaseHelper(this);
        dbList= new ArrayList<DatabaseModel>();
        dbList = helpher.getDataFromDB();
        btnConfirm  =(Button)findViewById(R.id.btnConfirm);
        btnCancel =(Button)findViewById(R.id.btnCancel);
        btnConfirm.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
                // Toast.makeText(ReserveActivity.this, "Thank you for your response", Toast.LENGTH_LONG);
                dbList.get(position).setResponsed("true");
                startActivity(new Intent(ResponseActivity.this, ReserveActivity.class));
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(ResponseActivity.this, "Request Declined", Toast.LENGTH_LONG);
                startActivity(new Intent(ResponseActivity.this, ReserveActivity.class));
            }
        });

        if(dbList.size()>0){
            String name= dbList.get(position).getName();
            String departure=dbList.get(position).getDeparture();
            String destination=dbList.get(position).getDestination();
            String date=dbList.get(position).getDate();
            String time=dbList.get(position).getTime();
            tvname.setText(name);
            tvdeparture.setText(departure);
            tvdestination.setText(destination);
            tvdate.setText(date);
            tvtime.setText(time);
        }

        Toast.makeText(ResponseActivity.this, dbList.toString(), Toast.LENGTH_LONG);
    }

}



