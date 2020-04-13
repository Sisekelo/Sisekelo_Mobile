package com.example.dell.sisekelo_mobile.model;

/*SISEKELO DLAMINI S1719039*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class DataReader
{
    private String urlString = "https://quakes.bgs.ac.uk/feeds/WorldSeismology.xml";
    private String rssString = "";

    public String getRssString()
    {
        return rssString;
    }

    public void FetchRSS()
    {
        try
        {
            URL url = new URL(urlString);
            URLConnection conn = (URLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String inputLine;

            while ((inputLine = in .readLine()) != null)
            {
                rssString += inputLine;
            }
            in .close();

        }

        catch (Exception e)

        {
            System.out.println(e.toString());
        }

    }

}
