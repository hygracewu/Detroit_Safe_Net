package com.project.mhack8.mhk8;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import database.DatabaseModel;

//import android.os.PersistableBundle;

public class ProfileActivity extends CommonActivity{

    private ArrayList<DatabaseModel> reservesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerAdapter rAdapter;
    private  int prevPageId=0;
    //private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            prevPageId = intent.getIntExtra("page id", ProfileActivity.PAGE_ID);
        }

    }



}
