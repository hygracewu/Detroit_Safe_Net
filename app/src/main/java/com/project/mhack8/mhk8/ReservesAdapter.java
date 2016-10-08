package com.project.mhack8.mhk8;

/**
 * Created by Grace on 2016/10/8.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ReservesAdapter extends RecyclerView.Adapter<ReservesAdapter.MyViewHolder> {

    private ArrayList<Reservation> reservesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, username, date, time;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            username = (TextView) view.findViewById(R.id.username);
            date = (TextView) view.findViewById(R.id.date);
            time = (TextView) view.findViewById(R.id.time);
        }
    }


    public ReservesAdapter(ArrayList<Reservation> reservesList) {
        this.reservesList = reservesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reserve_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Reservation reserve = reservesList.get(position);
        holder.title.setText(reserve.getPath());
        holder.username.setText(reserve.getUsername());
        holder.date.setText(reserve.getDate());
        holder.time.setText(reserve.getTime());
    }

    @Override
    public int getItemCount() {
        return reservesList.size();
    }

    /*@Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }*/
}