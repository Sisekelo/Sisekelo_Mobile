package com.example.dell.sisekelo_mobile.view;

/*SISEKELO DLAMINI S1719039*/

import android.content.Intent;
import android.os.Bundle;

import com.example.dell.sisekelo_mobile.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class Results extends AppCompatActivity {

    TextView textView_deepest,textView_shallowest,textView_largest,textView_north,textView_south,textView_west,textView_east;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_results );
        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setTitle("Results");

        Intent intent = getIntent();
        final String data_deepest = intent.getStringExtra("deepest");
        final String data_shallowest = intent.getStringExtra("shallowest");
        final String data_largest = intent.getStringExtra("largest");
        final String data_north = intent.getStringExtra("northest");
        final String data_south = intent.getStringExtra("southest");
        final String data_west = intent.getStringExtra("westest");
        final String data_east = intent.getStringExtra("eastest");

        String[] deepest_properties = data_deepest.trim().split("\n");
        String[] shallowest_properties = data_shallowest.trim().split("\n");
        String[] largest_properties = data_largest.trim().split("\n");
        String[] north_properties = data_north.trim().split("\n");
        String[] south_properties = data_south.trim().split("\n");
        String[] west_properties = data_west.trim().split("\n");
        String[] east_properties = data_east.trim().split("\n");


        textView_deepest = findViewById(R.id.textView_deep);
        textView_shallowest = findViewById(R.id.textView_shallow);
        textView_largest = findViewById(R.id.textView_largest);
        textView_north = findViewById( R.id.textView_north );
        textView_south = findViewById( R.id.textView_south );
        textView_west = findViewById( R.id.textView_west );
        textView_east = findViewById( R.id.textView_east );

        textView_deepest.setText( deepest_properties[4]+" with depth "+deepest_properties[1]+" KM" );
        textView_shallowest.setText( shallowest_properties[4]+" with depth "+shallowest_properties[1]+" KM" );
        textView_largest.setText( largest_properties[4]+" with Magnitude "+largest_properties[7] );
        textView_north.setText( largest_properties[4]+" with Latitude "+largest_properties[5] );

        if(!data_east.equals( "" )){
            textView_east.setText( largest_properties[4]+" with Latitude "+largest_properties[5] );
            textView_east.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent myIntent = new Intent(Results.this, Details.class);
                    myIntent.putExtra("info", data_east);
                    Results.this.startActivity(myIntent);
                }
            } );
        }
        else{
            textView_east.setText( "None");
        }

        if(!data_south.equals( "" )){
            textView_south.setText( largest_properties[4]+" with Latitude "+largest_properties[5] );
            textView_south.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent myIntent = new Intent(Results.this, Details.class);
                    myIntent.putExtra("info", data_south);
                    Results.this.startActivity(myIntent);
                }
            } );
        }
        else{
            textView_south.setText( "None");
        }

        if(!data_west.equals( "" )){
            textView_west.setText( west_properties[4]+" with Latitude "+west_properties[5] );
            textView_west.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent myIntent = new Intent(Results.this, Details.class);
                    myIntent.putExtra("info", data_west);
                    Results.this.startActivity(myIntent);
                }
            } );
        }
        else{
            textView_south.setText( "None");
        }


        textView_deepest.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(Results.this, Details.class);
                myIntent.putExtra("info", data_deepest);
                Results.this.startActivity(myIntent);
            }
        } );

        textView_shallowest.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(Results.this, Details.class);
                myIntent.putExtra("info", data_shallowest);
                Results.this.startActivity(myIntent);
            }
        } );

        textView_largest.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(Results.this, Details.class);
                myIntent.putExtra("info", data_largest);
                Results.this.startActivity(myIntent);
            }
        } );

        textView_north.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(Results.this, Details.class);
                myIntent.putExtra("info", data_north);
                Results.this.startActivity(myIntent);
            }
        } );


        FloatingActionButton fab = findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(Results.this, MainActivity.class);
                Results.this.startActivity(myIntent);
            }
        } );
    }

}
