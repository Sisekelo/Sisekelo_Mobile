package com.example.dell.sisekelo_mobile.view;

/*SISEKELO DLAMINI S1719039*/

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dell.sisekelo_mobile.R;
import com.example.dell.sisekelo_mobile.controller.Controller;
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
    TextView search_by_name;

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
        search_by_name = findViewById( R.id.textView_search_by_roadID);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        Intent intent = getIntent();
        final String data_feed = intent.getStringExtra("data_feed");



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
                    String deepest_info = Controller.getDeepest( quakes, finalDate, finalDate1 );

                    //shallowest
                    String shallowest_info = Controller.getShallowest( quakes, finalDate, finalDate1 );

                    //largest
                    String largest_info = Controller.getLargest( quakes, finalDate, finalDate1 );

                    //northest
                    String north_info = Controller.getNorth( quakes, finalDate, finalDate1 );

                    //southest
                    String south_info = Controller.getSouth( quakes, finalDate, finalDate1 );

                    //west
                    String west_info = Controller.getWest( quakes, finalDate, finalDate1 );

                    //east
                    String east_info = Controller.getEast( quakes, finalDate, finalDate1 );

                    Intent myIntent = new Intent( Search.this, Results.class );
                    myIntent.putExtra( "deepest", deepest_info );
                    myIntent.putExtra( "shallowest", shallowest_info );
                    myIntent.putExtra( "largest", largest_info );
                    myIntent.putExtra( "northest", north_info );
                    myIntent.putExtra( "southest", south_info );
                    myIntent.putExtra( "westest", west_info );
                    myIntent.putExtra( "eastest", east_info );
                    Search.this.startActivity( myIntent );

                }
            }
        });

        search_by_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(Search.this, Search_Name.class);
                myIntent.putExtra("data_feed", data_feed);
                Search.this.startActivity(myIntent);
            }
        });


    }

}
