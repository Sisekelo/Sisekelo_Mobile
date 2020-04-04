package  com.example.dell.sisekelo_mobile;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.dell.sisekelo_mobile.model.DataParser;
import com.example.dell.sisekelo_mobile.model.DataReader;
import com.example.dell.sisekelo_mobile.model.Details;
import com.example.dell.sisekelo_mobile.model.WidgetClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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
    private ProgressBar spinner;


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


        spinner = findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);
        asyncRSSParser.execute();

        ListView_LIST.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final WidgetClass object_selected = rssParser.earthquakeList.get(position);

                String quackInfo = "";

                quackInfo += object_selected.getCategory() + "\n";
                quackInfo += object_selected.getDepth() + "\n";
                quackInfo += object_selected.getDescription() + "\n";
                quackInfo += object_selected.getLink() + "\n";
                quackInfo += object_selected.getLocation() + "\n";
                quackInfo += object_selected.getLongitude() + "\n";
                quackInfo += object_selected.getLatitude() + "\n";
                quackInfo += object_selected.getMagnitude() + "\n";
                quackInfo += object_selected.getPubDate() + "\n";
                quackInfo += object_selected.getTitle();


                Snackbar.make(view, ""+ object_selected.getDescription(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent myIntent = new Intent(MainActivity.this, Details.class);
                myIntent.putExtra("info", quackInfo);
                MainActivity.this.startActivity(myIntent);



                /*Intent myIntent = new Intent(MainActivity.this, Roadwork_detail.class);
                myIntent.putExtra("title", object_selected.getTitle());
                myIntent.putExtra("pubDate", object_selected.getPubDate());
                myIntent.putExtra("description", object_selected.getDescription());
                myIntent.putExtra("link", object_selected.getLink());
                myIntent.putExtra("coordinates", object_selected.getCoordinates());
                MainActivity.this.startActivity(myIntent);*/
            }
        });



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
            spinner.setVisibility(View.GONE);
            // END
        }

    }

} // End of MainActivity