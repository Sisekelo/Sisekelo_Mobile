package com.example.dell.sisekelo_mobile.model;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.dell.sisekelo_mobile.R;

public class Details extends AppCompatActivity {

    TextView textView_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_details );
        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        Intent intent = getIntent();
        final String data = intent.getStringExtra("info");

        String[] deepest_properties = data.trim().split("\n");

        for(int i = 0 ; i < deepest_properties.length ; i++){

            Log.d( "deep", ""+deepest_properties[i]);

        }

        textView_title = (TextView) findViewById( R.id.textView_title_details );
        textView_title.setText( deepest_properties[3] );



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
