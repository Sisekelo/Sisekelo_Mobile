package com.example.dell.sisekelo_mobile;

/*SISEKELO DLAMINI S1719039*/

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.dell.sisekelo_mobile.model.DataParser;
import com.example.dell.sisekelo_mobile.model.WidgetClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class Search extends AppCompatActivity {

    EditText start_date, end_date;
    Button search_button;

    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_search );
        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setTitle("Search for earthquakes by date");

        start_date = (EditText) findViewById(R.id.input_date_start);
        end_date = (EditText) findViewById(R.id.input_date_end);
        search_button = (Button) findViewById( R.id.button_date_search );

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        Intent intent = getIntent();
        String data_feed = intent.getStringExtra("data_feed");



        //get deepest earth quack
        DataParser rssParser = new DataParser();
        rssParser.parseRSSString(data_feed);
        final LinkedList<WidgetClass> quakes = rssParser.earthquakeList;

        final SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");

        final Date[] date1 = {null};
        final Date[] date2 = {null};

        final Calendar c = Calendar.getInstance();
        final int mYear = c.get(Calendar.YEAR);
        final int mMonth = c.get(Calendar.MONTH);
        final int mDay = c.get(Calendar.DAY_OF_MONTH);


        start_date.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog;
                datePickerDialog = new DatePickerDialog(Search.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                start_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();


            }
        });

        end_date.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog;
                datePickerDialog = new DatePickerDialog(Search.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                end_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();


            }
        });





        search_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String string_end_date = end_date.getText().toString();
                final String string_start_date = start_date.getText().toString();

                try {
                    date1[0] =formatter1.parse( string_start_date );
                    date2[0] = formatter1.parse( string_end_date );

                } catch (ParseException e) {
                    e.printStackTrace();
                }


                final Date finalDate = date1[0];
                final Date finalDate1 = date2[0];

                if(finalDate.after( finalDate1 )){

                    AlertDialog alertDialog = new AlertDialog.Builder(Search.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("Your Start date is after the end date");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Reload",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Intent intent = getIntent();
                                    finish();
                                    startActivity(intent);
                                }
                            });
                    alertDialog.show();

                }
                else {
                    //deepest
                    String deepest_info = getDeepest( quakes, finalDate, finalDate1 );

                    //shallowest
                    String shallowest_info = getShallowest( quakes, finalDate, finalDate1 );

                    //largest
                    String largest_info = getLargest( quakes, finalDate, finalDate1 );

                    //northest
                    String north_info = getNorth( quakes, finalDate, finalDate1 );

                    //southest
                    String south_info = getSouth( quakes, finalDate, finalDate1 );

                    Intent myIntent = new Intent( Search.this, Results.class );
                    myIntent.putExtra( "deepest", deepest_info );
                    myIntent.putExtra( "shallowest", shallowest_info );
                    myIntent.putExtra( "largest", largest_info );
                    myIntent.putExtra( "northest", north_info );
                    myIntent.putExtra( "southest", south_info );
                    Search.this.startActivity( myIntent );

                }
            }
        });


    }

    private String getSouth(LinkedList<WidgetClass> quakes, Date date1, Date date2) {

        double mauritius_lat = -20.3;
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

            String cardinal = getNorthOrSouth(current_lat,mauritius_lat);

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

    private String getNorth(LinkedList<WidgetClass> quakes, Date date1, Date date2) {

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

            String cardinal = getNorthOrSouth(current_lat,mauritius_lat);

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

    private String getNorthOrSouth(double current_lat, double mauritius_lat) {

        double distance = current_lat - mauritius_lat;

        if(distance > 0) {
            return "north";
        }
        else{
            return "south";
        }

    }

    private String getLargest(LinkedList<WidgetClass> quakes, Date date1, Date date2) {

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

    private String getShallowest(LinkedList<WidgetClass> quakes, Date date1, Date date2) {

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

    private String getDeepest(LinkedList<WidgetClass> quakes, Date date1, Date date2){


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

    private boolean isDateCorrect(Date date1, Date date2, Date date3) {
        return date3.after( date1 ) && date3.before( date2 );
    }

}
