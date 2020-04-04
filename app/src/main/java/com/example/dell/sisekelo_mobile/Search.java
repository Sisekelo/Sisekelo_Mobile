package com.example.dell.sisekelo_mobile;

import android.content.Intent;
import android.os.Bundle;

import com.example.dell.sisekelo_mobile.model.DataParser;
import com.example.dell.sisekelo_mobile.model.WidgetClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

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

        //start_date.setText(setDate(year, month+1, day));



        Intent intent = getIntent();
        String data_feed = intent.getStringExtra("data_feed");



        //get deepest earth quack
        DataParser rssParser = new DataParser();
        rssParser.parseRSSString(data_feed);
        final LinkedList<WidgetClass> quakes = rssParser.earthquakeList;

        final SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");

        final Date[] date1 = {null};
        final Date[] date2 = {null};




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

                //deepest
                String deepest_info = getDeepest(quakes, finalDate, finalDate1);

                //shallowest
                String shallowest_info = getShallowest(quakes, finalDate, finalDate1);

                //largest
                String largest_info = getLargest(quakes, finalDate, finalDate1);

                Intent myIntent = new Intent(Search.this, Results.class);
                myIntent.putExtra("deepest", deepest_info);
                myIntent.putExtra("shallowest", shallowest_info);
                myIntent.putExtra("largest", largest_info);
                Search.this.startActivity(myIntent);


            }
        });


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
