package com.project.mhack8.mhk8;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import database.DatabaseHelper;
import database.DatabaseModel;

//import android.os.PersistableBundle;

public class ReserveActivity extends CommonActivity {

    DatabaseHelper helpher;
    List<DatabaseModel> dbList;
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public int prevPageId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);
        Intent intent = getIntent();
        prevPageId = intent.getIntExtra("page id", CommonActivity.PAGE_ID);

        helpher = new DatabaseHelper(ReserveActivity.this);
        helpher.insertIntoDB("Eric", "Detroit", "Ann Arbor", "10.09", "17:30");
        helpher.insertIntoDB("Grace", "Ann Arbor", "Detroit", "10.10", "17:30");

        helpher = new DatabaseHelper(this);
        dbList= new ArrayList<DatabaseModel>();
        dbList = helpher.getDataFromDB();


        mRecyclerView = (RecyclerView)findViewById(R.id.recycleview);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new RecyclerAdapter(this,dbList);
        mRecyclerView.setAdapter(mAdapter);

    }

}
