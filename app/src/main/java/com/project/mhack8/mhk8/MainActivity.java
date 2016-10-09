package com.project.mhack8.mhk8;

import android.content.Intent;
import android.os.Bundle;
//import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends CommonActivity{

    private ArrayList<Reservation> reservesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ReservesAdapter rAdapter;
    private  int prevPageId=0;
    //private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            prevPageId = intent.getIntExtra("page id", MainActivity.PAGE_ID);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setButton(R.id.button, 1);
        setButton(R.id.button2, 2);
        setButton(R.id.button3, 3);
        setButton(R.id.button4, 4);

    }



}
