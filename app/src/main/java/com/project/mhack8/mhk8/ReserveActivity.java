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

    public final int pageid = 1;
    private ArrayList<Reservation> reservesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ReservesAdapter rAdapter;
    private int prevPageId;
    public String []str = new String[6];
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
                str[0] = reserve.getUsername();
                str[1] = reserve.getStart();
                str[2] = reserve.getDestination();
                str[3] = reserve.getDate();
                str[4] = reserve.getTime();
                str[5] = String.valueOf(position);

                Toast.makeText(getApplicationContext(), reserve.getPath() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ReserveActivity.this, ResponseActivity.class);
                intent.putExtra("info", str);
                startActivity(intent);
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
