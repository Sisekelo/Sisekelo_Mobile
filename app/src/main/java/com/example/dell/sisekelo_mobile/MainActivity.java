package  com.example.dell.sisekelo_mobile;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dell.sisekelo_mobile.model.DataParser;
import com.example.dell.sisekelo_mobile.model.DataReader;
import com.example.dell.sisekelo_mobile.model.WidgetClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private String result;
    // Traffic Scotland URLs
    //private String urlSource = "https://trafficscotland.org/rss/feeds/roadworks.aspx";
    //private String urlSource = "https://trafficscotland.org/rss/feeds/plannedroadworks.aspx";
    private String urlSource = "https://quakes.bgs.ac.uk/feeds/WorldSeismology.xml";
    private ListView ListView_LIST;
    private ArrayAdapter arrayAdapter;

    DataReader rssReader = new DataReader();
    DataParser rssParser = new DataParser();
    AsyncRSSParser asyncRSSParser = new AsyncRSSParser();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Home: All Earthquakes");

        ListView_LIST = findViewById(R.id.ListView_LIST);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String data_feed = rssReader.getRssString();

                Intent myIntent = new Intent(MainActivity.this, Search.class);
                myIntent.putExtra("data_feed", data_feed);
                MainActivity.this.startActivity(myIntent);
            }
        });

        asyncRSSParser.execute();

    }

    @Override
    public void onClick(View view) {

    }

    private class AsyncRSSParser extends AsyncTask<Void, Void, LinkedList<WidgetClass>> {


        protected LinkedList<WidgetClass> doInBackground(Void... nothing) {

            //get the data
            rssReader.FetchRSS();



            // Parse the xml of the RSS into a LinkedList of Top Stories
            rssParser.parseRSSString(rssReader.getRssString());

            // Return the LinkedList containing earthquakes
            return rssParser.earthquakeList;
        }

        // Print the First Element from the earthquakeList returned by doInBackground()
        protected void onPostExecute(LinkedList<WidgetClass> earthquakeList) {



            arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, rssParser.titleList);
            ListView_LIST.setAdapter(arrayAdapter);
            // END
        }

    }

} // End of MainActivity