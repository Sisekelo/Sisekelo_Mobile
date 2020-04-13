package com.example.dell.sisekelo_mobile.view;

/*SISEKELO DLAMINI S1719039*/

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.dell.sisekelo_mobile.R;
import com.example.dell.sisekelo_mobile.model.DataParser;
import com.example.dell.sisekelo_mobile.model.WidgetClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;

public class Search_Name extends AppCompatActivity {

    TextView search_by_date;
    Button button_country_search;
    EditText input_country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_search_name );
        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setTitle("Search for nearest earthquake by country");

        Intent intent = getIntent();
        final String data_feed = intent.getStringExtra("data_feed");

        search_by_date = findViewById( R.id.textView_search_by_date );
        button_country_search = findViewById( R.id.button_country_search );
        input_country = findViewById( R.id.input_county );

        button_country_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataParser rssParser = new DataParser();
                rssParser.parseRSSString(data_feed);
                final LinkedList<WidgetClass> quakes = rssParser.earthquakeList;
                final String country = String.valueOf(input_country.getText());




                String country_details = getCountryDetails(quakes,country);

                if(country_details.equalsIgnoreCase( "" )){

                    AlertDialog alertDialog = new AlertDialog.Builder(Search_Name.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("No earthquakes in the past year for this country");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Reload",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                    input_country.setText( "" );
                                }
                            });
                    alertDialog.show();

                }
                else{

                    Intent myIntent = new Intent(Search_Name.this, Details.class);
                    myIntent.putExtra("info", country_details);
                    Search_Name.this.startActivity(myIntent);

                }


            }
        });

        search_by_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(Search_Name.this, Search.class);
                myIntent.putExtra("data_feed", data_feed);
                Search_Name.this.startActivity(myIntent);
            }
        });


    }

    private String getCountryDetails(LinkedList<WidgetClass> quakes, String country) {

        int depth_holder = 0;
        String country_info = "";

        for(int i = 0 ; i < quakes.size() ; i++){

            int current_depth = Integer.parseInt(quakes.get(i).getDepth());

            if(quakes.get( i ).getLocation().equalsIgnoreCase( country.toLowerCase() )){

                if(current_depth > depth_holder){
                    depth_holder = current_depth;

                    country_info = "";

                    country_info += quakes.get( i ).getCategory() + "\n";
                    country_info += quakes.get( i ).getDepth() + "\n";
                    country_info += quakes.get( i ).getDescription() + "\n";
                    country_info += quakes.get( i ).getLink() + "\n";
                    country_info += quakes.get( i ).getLocation() + "\n";
                    country_info += quakes.get( i ).getLongitude() + "\n";
                    country_info += quakes.get( i ).getLatitude() + "\n";
                    country_info += quakes.get( i ).getMagnitude() + "\n";
                    country_info += quakes.get( i ).getPubDate() + "\n";
                    country_info += quakes.get( i ).getTitle();

                }
            }
        }

        return country_info;
    }


}
