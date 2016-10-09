package database;

/**
 * Created by PRABHU on 11/12/2015.
 */
public class DatabaseModel {
    private String name;
    private String departure;
    private String destination;
    private String date;
    private String time;
    private String responsed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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
        return "from "+departure+" to "+destination;
    }

    public String getResponsed() {
        return responsed;
    }

    public void setResponsed(String responsed) {
        this.responsed = responsed;
    }
}
