package com.project.mhack8.mhk8;

import android.os.Bundle;
//import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Reservation> reservesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ReservesAdapter rAdapter;
    //private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        rAdapter = new ReservesAdapter(reservesList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //dbHandler = new DBHandler(MainActivity.this);


        /**
                * CRUD Operations
                * */
        // Inserting Contacts
        //Log.e("Insert: ", "Inserting...");
        //dbHandler.addUser(new Reservation("a", "b", "Grace", "10/07/16", "12:00"));
        //dbHandler.addUser(new Reservation("c", "d", "Eric", "10/08/16", "18:00"));
        // Reading all contacts
        //Log.d("Reading: ", "reading contacts..");
        //reservesList = dbHandler.getAllUsers();
        recyclerView.setAdapter(rAdapter);
        rAdapter.notifyDataSetChanged();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Reservation reserve = reservesList.get(position);
                Toast.makeText(getApplicationContext(), reserve.getPath() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareReserveData();
    }

    private void prepareReserveData() {
        Reservation reserve = new Reservation("a", "b", "Grace", "10/07/16", "12:00");
        reservesList.add(reserve);

        reserve = new Reservation("c", "d", "Eric", "10/08/16", "18:00");
        reservesList.add(reserve);

        rAdapter.notifyDataSetChanged();
    }

}
