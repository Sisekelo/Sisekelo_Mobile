package com.example.dell.sisekelo_mobile.controller;

import android.util.Log;

import com.example.dell.sisekelo_mobile.model.WidgetClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class Controller {


    static double mauritius_lat = -20.3;
    static double mauritius_long = 57.5;

    public static String getEast(LinkedList<WidgetClass> quakes, Date date1, Date date2) {

        double distance_holder = 0;
        String east_info = "";

        for (int i = 0; i < quakes.size(); i++) {

            double current_long = Double.parseDouble( quakes.get( i ).getLongitude() );

            String date = quakes.get( i ).getPubDate();
            SimpleDateFormat formatter5 = new SimpleDateFormat( "E, dd MMM yyyy HH:mm:ss" );

            Date date3 = null;
            try {
                date3 = formatter5.parse( date );
            } catch (ParseException e) {
                e.printStackTrace();
            }

            boolean datetruth = isDateCorrect( date1, date2, date3 );

            String cardinal = isEastOrWest( current_long, mauritius_long );

            double distance = Math.abs( current_long - mauritius_long );

            if (i == 0) {
                distance_holder = distance;
            }


            if (datetruth) {

                switch (cardinal) {
                    case "east":
                        // code block
                        if (distance <= distance_holder) {
                            distance_holder = distance;

                            east_info = "";

                            east_info += quakes.get( i ).getCategory() + "\n";
                            east_info += quakes.get( i ).getDepth() + "\n";
                            east_info += quakes.get( i ).getDescription() + "\n";
                            east_info += quakes.get( i ).getLink() + "\n";
                            east_info += quakes.get( i ).getLocation() + "\n";
                            east_info += quakes.get( i ).getLongitude() + "\n";
                            east_info += quakes.get( i ).getLatitude() + "\n";
                            east_info += quakes.get( i ).getMagnitude() + "\n";
                            east_info += quakes.get( i ).getPubDate() + "\n";
                            east_info += quakes.get( i ).getTitle();


                        }
                        break;
                    default:
                        // code block
                }
            }
        }

        return east_info;
    }

    public static String getWest(LinkedList<WidgetClass> quakes, Date date1, Date date2) {

        double distance_holder = 0;
        String west_info = "";

        for (int i = 0; i < quakes.size(); i++) {

            double current_long = Double.parseDouble( quakes.get( i ).getLongitude() );

            String date = quakes.get( i ).getPubDate();
            SimpleDateFormat formatter5 = new SimpleDateFormat( "E, dd MMM yyyy HH:mm:ss" );

            Date date3 = null;
            try {
                date3 = formatter5.parse( date );
            } catch (ParseException e) {
                e.printStackTrace();
            }

            boolean datetruth = isDateCorrect( date1, date2, date3 );

            String cardinal = isEastOrWest( current_long, mauritius_long );

            double distance = Math.abs( current_long - mauritius_long );

            if (i == 0) {
                distance_holder = distance;
            }


            if (datetruth) {

                switch (cardinal) {
                    case "west":
                        // code block
                        if (distance <= distance_holder) {
                            distance_holder = distance;

                            west_info = "";

                            west_info += quakes.get( i ).getCategory() + "\n";
                            west_info += quakes.get( i ).getDepth() + "\n";
                            west_info += quakes.get( i ).getDescription() + "\n";
                            west_info += quakes.get( i ).getLink() + "\n";
                            west_info += quakes.get( i ).getLocation() + "\n";
                            west_info += quakes.get( i ).getLongitude() + "\n";
                            west_info += quakes.get( i ).getLatitude() + "\n";
                            west_info += quakes.get( i ).getMagnitude() + "\n";
                            west_info += quakes.get( i ).getPubDate() + "\n";
                            west_info += quakes.get( i ).getTitle();


                        }
                        break;
                    case "north":
                        // code block
                        break;
                    default:
                        // code block
                }


            }
        }

        return west_info;
    }

    public static String getSouth(LinkedList<WidgetClass> quakes, Date date1, Date date2) {

        double distance_holder = 0;
        String southest_info = "";

        for(int i = 0 ; i < quakes.size() ; i++){

            double current_lat = Double.parseDouble(quakes.get(i).getLatitude());

            String date = quakes.get( i ).getPubDate();
            SimpleDateFormat formatter5=new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");

            Date date3= null;
            try {
                date3 = formatter5.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            boolean datetruth = isDateCorrect( date1, date2, date3 );

            String cardinal = isNorthOrSouth(current_lat,mauritius_lat);

            double distance = Math.abs( current_lat - mauritius_lat );

            if(i == 0){
                distance_holder = distance;
            }



            if(datetruth){

                switch(cardinal) {
                    case "south":
                        // code block
                        if(distance >= distance_holder){
                            distance_holder = distance;

                            southest_info = "";

                            southest_info += quakes.get( i ).getCategory() + "\n";
                            southest_info += quakes.get( i ).getDepth() + "\n";
                            southest_info += quakes.get( i ).getDescription() + "\n";
                            southest_info += quakes.get( i ).getLink() + "\n";
                            southest_info += quakes.get( i ).getLocation() + "\n";
                            southest_info += quakes.get( i ).getLongitude() + "\n";
                            southest_info += quakes.get( i ).getLatitude() + "\n";
                            southest_info += quakes.get( i ).getMagnitude() + "\n";
                            southest_info += quakes.get( i ).getPubDate() + "\n";
                            southest_info += quakes.get( i ).getTitle();


                        }
                        break;
                    case "north":
                        // code block
                        break;
                    default:
                        // code block
                }





            }
        }


        return southest_info;
    }

    public static String getNorth(LinkedList<WidgetClass> quakes, Date date1, Date date2) {

        double mauritius_lat = -20.3;
        double distance_holder = 0;
        String northest_info = "";

        for(int i = 0 ; i < quakes.size() ; i++){

            double current_lat = Double.parseDouble(quakes.get(i).getLatitude());

            String date = quakes.get( i ).getPubDate();
            SimpleDateFormat formatter5=new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");

            Date date3= null;
            try {
                date3 = formatter5.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            boolean datetruth = isDateCorrect( date1, date2, date3 );

            String cardinal = isNorthOrSouth(current_lat,mauritius_lat);

            double distance = Math.abs( current_lat - mauritius_lat );

            if(i == 0){
                distance_holder = distance;
            }



            if(datetruth){

                Log.d("test_north","TRUE"+quakes.get( i ).getLatitude());


                switch(cardinal) {
                    case "north":
                        // code block
                        if(distance <= distance_holder){
                            distance_holder = distance;

                            northest_info = "";

                            northest_info += quakes.get( i ).getCategory() + "\n";
                            northest_info += quakes.get( i ).getDepth() + "\n";
                            northest_info += quakes.get( i ).getDescription() + "\n";
                            northest_info += quakes.get( i ).getLink() + "\n";
                            northest_info += quakes.get( i ).getLocation() + "\n";
                            northest_info += quakes.get( i ).getLongitude() + "\n";
                            northest_info += quakes.get( i ).getLatitude() + "\n";
                            northest_info += quakes.get( i ).getMagnitude() + "\n";
                            northest_info += quakes.get( i ).getPubDate() + "\n";
                            northest_info += quakes.get( i ).getTitle();


                        }
                        break;
                    case "south":
                        // code block
                        break;
                    default:
                        // code block
                }





            }
        }


        return northest_info;

    }

    private static String isNorthOrSouth(double current_lat, double mauritius_lat) {

        double distance = current_lat - mauritius_lat;

        if(distance > 0) {
            return "north";
        }
        else{
            return "south";
        }

    }

    private static String isEastOrWest(double current_long, double mauritius_long) {

        double distance = current_long - mauritius_long;

        if(distance > 0) {
            return "west";
        }
        else{
            return "east";
        }
    }

    public static String getLargest(LinkedList<WidgetClass> quakes, Date date1, Date date2) {

        double magnitude_holder = 0.0;
        String largest_info = "";

        for(int i = 0 ; i < quakes.size() ; i++){

            double current_magnitude = Double.parseDouble(quakes.get(i).getMagnitude());

            String date = quakes.get( i ).getPubDate();
            SimpleDateFormat formatter5=new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");

            Date date3= null;
            try {
                date3 = formatter5.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }



            boolean datetruth = isDateCorrect( date1, date2, date3 );


            if(datetruth){

                if(current_magnitude > magnitude_holder){
                    magnitude_holder = current_magnitude;

                    largest_info = "";

                    largest_info += quakes.get( i ).getCategory() + "\n";
                    largest_info += quakes.get( i ).getDepth() + "\n";
                    largest_info += quakes.get( i ).getDescription() + "\n";
                    largest_info += quakes.get( i ).getLink() + "\n";
                    largest_info += quakes.get( i ).getLocation() + "\n";
                    largest_info += quakes.get( i ).getLongitude() + "\n";
                    largest_info += quakes.get( i ).getLatitude() + "\n";
                    largest_info += quakes.get( i ).getMagnitude() + "\n";
                    largest_info += quakes.get( i ).getPubDate() + "\n";
                    largest_info += quakes.get( i ).getTitle();

                    Log.d("test_deep",""+largest_info);
                    Log.d("test_deep","---------------------");
                }

            }

        }
        return largest_info;

    }

    public static String getShallowest(LinkedList<WidgetClass> quakes, Date date1, Date date2) {

        int depth_holder = 0;
        String shallowest_info = "";

        for(int i = 0 ; i < quakes.size() ; i++){

            int current_depth = Integer.parseInt(quakes.get(i).getDepth());

            if(i == 0){

                depth_holder = current_depth;
            }

            String date = quakes.get( i ).getPubDate();
            SimpleDateFormat formatter5=new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");

            Date date3= null;
            try {
                date3 = formatter5.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }



            boolean datetruth = isDateCorrect( date1, date2, date3 );


            if(datetruth){

                if(current_depth <= depth_holder){

                    Log.d("depth_test","**********************");
                    Log.d("depth_test",""+current_depth);
                    Log.d("depth_test","**********************"+depth_holder);
                    Log.d("depth_test","**********************");


                    depth_holder = current_depth;

                    shallowest_info = "";

                    shallowest_info += quakes.get( i ).getCategory() + "\n";
                    shallowest_info += quakes.get( i ).getDepth() + "\n";
                    shallowest_info += quakes.get( i ).getDescription() + "\n";
                    shallowest_info += quakes.get( i ).getLink() + "\n";
                    shallowest_info += quakes.get( i ).getLocation() + "\n";
                    shallowest_info += quakes.get( i ).getLongitude() + "\n";
                    shallowest_info += quakes.get( i ).getLatitude() + "\n";
                    shallowest_info += quakes.get( i ).getMagnitude() + "\n";
                    shallowest_info += quakes.get( i ).getPubDate() + "\n";
                    shallowest_info += quakes.get( i ).getTitle();
                }

            }

        }
        return shallowest_info;
    }

    public static String getDeepest(LinkedList<WidgetClass> quakes, Date date1, Date date2){


        int depth_holder = 0;
        String deepest_info = "";

        for(int i = 0 ; i < quakes.size() ; i++){

            int current_depth = Integer.parseInt(quakes.get(i).getDepth());

            String date = quakes.get( i ).getPubDate();
            SimpleDateFormat formatter5=new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");

            Date date3= null;
            try {
                date3 = formatter5.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }



            boolean datetruth = isDateCorrect( date1, date2, date3 );


            if(datetruth){

                if(current_depth > depth_holder){
                    depth_holder = current_depth;

                    deepest_info = "";

                    deepest_info += quakes.get( i ).getCategory() + "\n";
                    deepest_info += quakes.get( i ).getDepth() + "\n";
                    deepest_info += quakes.get( i ).getDescription() + "\n";
                    deepest_info += quakes.get( i ).getLink() + "\n";
                    deepest_info += quakes.get( i ).getLocation() + "\n";
                    deepest_info += quakes.get( i ).getLongitude() + "\n";
                    deepest_info += quakes.get( i ).getLatitude() + "\n";
                    deepest_info += quakes.get( i ).getMagnitude() + "\n";
                    deepest_info += quakes.get( i ).getPubDate() + "\n";
                    deepest_info += quakes.get( i ).getTitle();

                    Log.d("test_deep",""+deepest_info);
                    Log.d("test_deep","---------------------");
                }

            }

        }
        return deepest_info;
    }

    private static boolean isDateCorrect(Date date1, Date date2, Date date3) {
        return date3.after( date1 ) && date3.before( date2 );
    }


}
