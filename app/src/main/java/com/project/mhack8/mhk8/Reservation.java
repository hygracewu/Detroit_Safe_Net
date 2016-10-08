package com.project.mhack8.mhk8;

/**
 * Created by Grace on 2016/10/8.
 */

public class Reservation {
    //private int id;
    private String start, destination, username, date, time;

    //public Reservation() {
    //}

    public Reservation(String start, String destination, String username, String date, String time) {
        //this.id = id;
        this.start = start;
        this.destination = destination;
        this.username = username;
        this.date = date;
        this.time = time;
    }

    //public int getId() { return id; }

    //public void setId(int id) { this.id = id; }

    public String getStart() {
        return start;
    }

    public void setStart(String place) { this.start = place; }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String place) {
        this.destination = place;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPath() {
        return "from "+start+" to "+destination;
    }
}
