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

//import android.os.PersistableBundle;

public class ReserveActivity extends CommonActivity {
    private ArrayList<Reservation> reservesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ReservesAdapter rAdapter;
    private int prevPageId;
    //private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        Intent intent = getIntent();
        prevPageId = intent.getIntExtra("page id", CommonActivity.PAGE_ID);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        rAdapter = new ReservesAdapter(reservesList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

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
