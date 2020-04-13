package com.example.dell.sisekelo_mobile;

/*SISEKELO DLAMINI S1719039*/

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Results extends AppCompatActivity {

    TextView textView_deepest,textView_shallowest,textView_largest,textView_north,textView_south;

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

        String[] deepest_properties = data_deepest.trim().split("\n");
        String[] shallowest_properties = data_shallowest.trim().split("\n");
        String[] largest_properties = data_largest.trim().split("\n");
        String[] north_properties = data_north.trim().split("\n");
        String[] south_properties = data_south.trim().split("\n");




        for(int i = 0 ; i < deepest_properties.length ; i++){

            Log.d( "deep", "******Deepest**********");
            Log.d( "deep", ""+deepest_properties[i]);
            Log.d( "deep", "******Shallow**********");
            Log.d( "deep", ""+shallowest_properties[i]);
            Log.d( "deep", "******Largest**********");
            Log.d( "deep", ""+largest_properties[i]);
            Log.d( "deep", "******North**********");
            Log.d( "deep", ""+north_properties[i]);

        }

        textView_deepest = findViewById(R.id.textView_deep);
        textView_shallowest = findViewById(R.id.textView_shallow);
        textView_largest = findViewById(R.id.textView_largest);
        textView_north = findViewById( R.id.textView_north );
        textView_south = findViewById( R.id.textView_south );

        textView_deepest.setText( deepest_properties[4]+" with depth "+deepest_properties[1]+" KM" );
        textView_shallowest.setText( shallowest_properties[4]+" with depth "+shallowest_properties[1]+" KM" );
        textView_largest.setText( largest_properties[4]+" with Magnitude "+largest_properties[7] );
        textView_north.setText( largest_properties[4]+" with Latitude "+largest_properties[5] );

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
