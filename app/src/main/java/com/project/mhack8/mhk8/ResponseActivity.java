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
        String[] info = intent.getStringArrayExtra("info");

        tvname = ((TextView)findViewById(R.id.name));
        tvdeparture =(TextView)findViewById(R.id.departure);
        tvdestination =(TextView)findViewById(R.id.destination);
        tvdate =(TextView)findViewById(R.id.date);
        tvtime =(TextView)findViewById(R.id.time);

        tvname.setText("username: "+info[0]);
        tvdeparture.setText("departure: "+info[1]);
        tvdestination.setText("destination: "+info[2]);
        tvdate.setText("date: "+info[3]);
        tvtime.setText("time: "+info[4]);
        int position = Integer.valueOf(info[5]);

        /*
        helpher = new DatabaseHelper(this);
        dbList= new ArrayList<DatabaseModel>();
        dbList = helpher.getDataFromDB();
        */

        btnConfirm  =(Button)findViewById(R.id.btnConfirm);
        btnCancel =(Button)findViewById(R.id.btnCancel);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResponseActivity.this, ReserveActivity.class));
                /*
                TextView text = (TextView) findViewById(R.id.toast);
                text.setText("Response sent");
                Toast toast = new Toast(getApplicationContext());
                toast.show();
                */
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResponseActivity.this, ReserveActivity.class));
                /*
                TextView text = (TextView) findViewById(R.id.toast);
                text.setText("Request declined");
                Toast toast = new Toast(getApplicationContext());
                toast.show();
                */
            }
        });

    }

}
