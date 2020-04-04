package com.example.dell.sisekelo_mobile;

import android.content.Intent;
import android.os.Bundle;

import com.example.dell.sisekelo_mobile.model.Details;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Results extends AppCompatActivity {

    TextView textView_deepest,textView_shallowest,textView_largest;

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

        String[] deepest_properties = data_deepest.trim().split("\n");
        String[] shallowest_properties = data_shallowest.trim().split("\n");
        String[] largest_properties = data_largest.trim().split("\n");

        for(int i = 0 ; i < deepest_properties.length ; i++){

            Log.d( "deep", "******Deepest**********");
            Log.d( "deep", ""+deepest_properties[i]);
            Log.d( "deep", "******Shallow**********");
            Log.d( "deep", ""+shallowest_properties[i]);
            Log.d( "deep", "******Largest**********");
            Log.d( "deep", ""+largest_properties[i]);

        }

        textView_deepest = findViewById(R.id.textView_deep);
        textView_shallowest = findViewById(R.id.textView_shallow);
        textView_largest = findViewById(R.id.textView_largest);

        textView_deepest.setText( deepest_properties[4]+" with depth "+deepest_properties[1]+" KM" );
        textView_shallowest.setText( shallowest_properties[4]+" with depth "+shallowest_properties[1]+" KM" );
        textView_largest.setText( largest_properties[4]+" with Magnitude "+largest_properties[7] );

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


        FloatingActionButton fab = findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make( view, "Replace with your own action", Snackbar.LENGTH_LONG )
                        .setAction( "Action", null ).show();
            }
        } );
    }

}
