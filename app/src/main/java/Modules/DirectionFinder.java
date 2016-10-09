package Modules;

import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;
//import com.google.maps.android.SphericalUtil;
import android.location.Location;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import java.util.*;
import android.util.Log;
//import java.lang.*;


/**
 * Created by Mai Thanh Hiep on 4/3/2016.
 */
public class DirectionFinder {
    ////paste
    private static final String TAG="MyActivity";
    public class Crime_data extends AsyncTask<String, Void, String>{
        int n;
        Vector<LatLng> crime;
        Vector<Double> w;




        protected String loadMultipleCrimeData(String[] Urls, Double[] desig_ws) {
            crime = new Vector<LatLng>();
            w = new Vector<Double>();
            for (int i = 0; i < Urls.length; i++) {
                try {
                    URL url = new URL(Urls[i]);
                    InputStream is = url.openConnection().getInputStream();
                    StringBuffer buffer = new StringBuffer();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line + "\n");
                    }
                    parseData(buffer.toString(), desig_ws[i]);   //where parse string
                }
                catch (MalformedURLException e) {
                    e.printStackTrace();
                    Log.e(TAG,e.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG,e.toString());
                }
                catch(JSONException e){
                    e.printStackTrace();
                    Log.e(TAG,e.toString());
                }
                catch(Exception e){
                    Log.e(TAG,e.toString());
                }
                //return buffer.toString();

            }
            return null;
        }

        void parseData(String str, double desig_w) throws JSONException {
            JSONArray json = new JSONArray(str);
            for (int i = 0; i < json.length(); i++) {
                JSONObject e = json.getJSONObject(i);
                this.crime.add(new LatLng(e.getDouble("lon"), e.getDouble("lat")));
                w.add(desig_w);
            }
        }
        @Override
        protected String doInBackground(String... params) {
            String[] urls = new String[ ]{"https://data.detroitmi.gov/resource/b4hw-v6w2.json?$select=lon,lat&category=murder/information","https://data.detroitmi.gov/resource/b4hw-v6w2.json?$select=lon,lat&category=kidnaping"};
            Double[] ws = new Double[ ]{3.0,1.0};
            loadMultipleCrimeData(urls,ws);

            return null;
        }

        @Override
        protected void onPostExecute(String res)  {

            //do loadRawData, which must be after loadCrimeData
            try {
                new DownloadRawData().execute(createUrl());
            }
            catch(UnsupportedEncodingException e){

            }
        }
    }
    // /////////////
    private static final String DIRECTION_URL_API = "https://maps.googleapis.com/maps/api/directions/json?";
    private static final String GOOGLE_API_KEY = "AIzaSyDnwLF2-WfK8cVZt9OoDYJ9Y8kspXhEHfI";
    private DirectionFinderListener listener;
    private String origin;
    private String mode; // travel mode

    private String destination;
    Crime_data crime_data;
    public DirectionFinder(DirectionFinderListener listener, String origin, String destination,String mode) {
        this.listener = listener;
        this.origin = origin;
        this.destination = destination;
        this.mode=mode;
        this.crime_data = new Crime_data();
    }

    public void execute() throws UnsupportedEncodingException,JSONException {
        listener.onDirectionFinderStart();
        //load here
        /*String[] urls = new String[ ]{"https://data.detroitmi.gov/resource/b4hw-v6w2.json?$select=lon,lat&category=murder/information","https://data.detroitmi.gov/resource/b4hw-v6w2.json?$select=lon,lat&category=kidnaping"};
        Double[] ws = new Double[ ]{2.0,1.0};*/
        //crime_data.loadMultipleCrimeData(urls,ws);
        crime_data.execute();
        //load crime data complete

        //
        //new DownloadRawData().execute(createUrl());
    }

    private String createUrl() throws UnsupportedEncodingException {
        String urlOrigin = URLEncoder.encode(origin, "utf-8");
        String urlDestination = URLEncoder.encode(destination, "utf-8");
        String urlmode= URLEncoder.encode(mode, "utf-8");
        return DIRECTION_URL_API + "origin=" + urlOrigin+ "&mode=" + urlmode+ "&destination=" + urlDestination + "&key=" + GOOGLE_API_KEY;
    }

    private class DownloadRawData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String link = params[0];
            try {
                URL url = new URL(link);
                InputStream is = url.openConnection().getInputStream();
                StringBuffer buffer = new StringBuffer();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String res) {
            try {
                parseJSon(res);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
    double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    double[] LatLngToDouble(LatLng point){
        double[] d_point = new double[2];
        final double amp = 1.0;
        d_point[0] = (double)point.latitude*amp;
        d_point[1] = (double)point.longitude*amp;
        return  d_point;
    }
    /*
    //double computeDistByMid(double sx,double sy,double ex,double ey,double cx,double cy){  //

    double computeDistbyMid_Lat(LatLng a,LatLng b, LatLng c){
        //Distance();
        //double midX = (sx+ex)/2.0;
        //double midY = (sy+ey)/2.0;
        float[] results = new float[1];
        LatLng mid = new LatLng((a.latitude+b.latitude)/2,(a.longitude+b.longitude)/2);

        Location locationA = new Location("mid");
        locationA.setLatitude(mid.latitude);
        locationA.setLongitude(mid.longitude);
        Location locationB = new Location("C");
        locationB.setLatitude(c.latitude);
        locationB.setLongitude(c.longitude);
        double distance = locationA.distanceTo(locationB);
        return distance;
    }
     */
    private double meterDistanceBetweenPoints(double lat_a, double lng_a, double lat_b, double lng_b) {
        double pk = (double) (180.f/Math.PI);
        double a1 = lat_a / pk;
        double a2 = lng_a / pk;
        double b1 = lat_b / pk;
        double b2 = lng_b / pk;

        double t1 = Math.cos(a1)*Math.cos(a2)*Math.cos(b1)*Math.cos(b2);
        double t2 = Math.cos(a1)*Math.sin(a2)*Math.cos(b1)*Math.sin(b2);
        double t3 = Math.sin(a1)*Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);

        return 6366000*tt;}



    double computeCrimeTier(Vector<LatLng> crimes,Vector<Double> weights,LatLng start,LatLng end){
        double []crimeBuf= new double[2];
        double []start_point= new double[2];
        double []end_point= new double[2];
        start_point = LatLngToDouble(start);
        end_point = LatLngToDouble(end);
        double []mid= new double[2];
        mid[0]=(start_point[0]+end_point[0])/2;
        mid[1]=(start_point[1]+end_point[1])/2;

        double sum=0;
        double point_line_dist;
        for(int i=0; i<crimes.size() ;i++){

            crimeBuf = LatLngToDouble(crimes.get(i));

            point_line_dist = meterDistanceBetweenPoints(mid[0],mid[1],crimeBuf[1],crimeBuf[0])/1000;  ////distance:Km

            if (point_line_dist<0.5){
                sum += weights.get(i);
            }
        }
        return sum;
    }

    private void parseJSon(String data) throws JSONException {
        if (data == null)    return;
        List<Route> routes = new ArrayList<Route>();
        JSONObject jsonData = new JSONObject(data);
        JSONArray jsonRoutes = jsonData.getJSONArray("routes");
        for (int i = 0; i < jsonRoutes.length(); i++) {
            JSONObject jsonRoute = jsonRoutes.getJSONObject(i);
            //Route route = new Route();
            JSONObject overview_polylineJson = jsonRoute.getJSONObject("overview_polyline");
            JSONArray jsonLegs = jsonRoute.getJSONArray("legs");
            JSONObject jsonLeg = jsonLegs.getJSONObject(0);
            JSONObject jsonDistance = jsonLeg.getJSONObject("distance");
            JSONObject jsonDuration = jsonLeg.getJSONObject("duration");
            JSONObject jsonEndLocation = jsonLeg.getJSONObject("end_location");
            JSONObject jsonStartLocation = jsonLeg.getJSONObject("start_location");

            JSONArray J_step=jsonLeg.getJSONArray("steps");
            for(int j = 0; j < J_step.length(); j++){
                JSONObject jsonStep = J_step.getJSONObject(j);
                jsonDistance = jsonStep.getJSONObject("distance");
                jsonDuration = jsonStep.getJSONObject("duration");
                jsonEndLocation = jsonStep.getJSONObject("end_location");
                jsonStartLocation = jsonStep.getJSONObject("start_location");
                Route route = new Route();
                route.distance = new Distance(jsonDistance.getString("text"), jsonDistance.getInt("value"));
                route.duration = new Duration(jsonDuration.getString("text"), jsonDuration.getInt("value"));
                route.endAddress = jsonLeg.getString("end_address");
                route.startAddress = jsonLeg.getString("start_address");
                route.startLocation = new LatLng(jsonStartLocation.getDouble("lat"), jsonStartLocation.getDouble("lng"));
                route.endLocation = new LatLng(jsonEndLocation.getDouble("lat"), jsonEndLocation.getDouble("lng"));
                route.points = decodePolyLine(overview_polylineJson.getString("points"));
                route.c_tire=computeCrimeTier(crime_data.crime,crime_data.w,route.startLocation,route.endLocation);
                routes.add(route);
            }
            //

        }

        listener.onDirectionFinderSuccess(routes);
    }

    private List<LatLng> decodePolyLine(final String poly) {
        int len = poly.length();
        int index = 0;
        List<LatLng> decoded = new ArrayList<LatLng>();
        int lat = 0;
        int lng = 0;

        while (index < len) {
            int b;
            int shift = 0;
            int result = 0;
            do {
                b = poly.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = poly.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            decoded.add(new LatLng(
                    lat / 100000d, lng / 100000d
            ));
        }
        return decoded;
    }
}
