package com.project.mhack8.mhk8;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.w3c.dom.Text;

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
    String tvname,tvdeparture,tvdestination,tvdate,tvtime;
    Button btnConfirm,btnCancel;
    private int prevPageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);
        Intent intent = getIntent();
        String[] info = intent.getStringArrayExtra("info");

        tvname = info[0];
        tvdeparture = info[1];
        tvdestination = info[2];
        tvdate = info[3];
        tvtime = info[4];

        btnConfirm  =(Button)findViewById(R.id.btnConfirm);
        btnCancel =(Button)findViewById(R.id.btnCancel);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResponseActivity.this, ReserveActivity.class));
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResponseActivity.this, ReserveActivity.class));
            }
        });

        String txt = "Response to the request from" + tvname + ", from " + tvdeparture + " to " + tvdestination + ", " + tvdate + tvtime;
        Log.v("Toast: ", txt);

    }

}
