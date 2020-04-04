package com.example.dell.sisekelo_mobile.model;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;


public class DataParser {
    // Linked List containing top stories each with its own title, description and publication date
    public LinkedList < WidgetClass > earthquakeList = null;

    public LinkedList < String > titleList = null;


    public LinkedList < WidgetClass > parseRSSString(String rssString) {
        WidgetClass widget = null;

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(rssString));
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {

                // Found a start tag
                if (eventType == XmlPullParser.START_TAG) {
                    // Check which Tag we have
                    if (xpp.getName().equalsIgnoreCase("channel")) {
                        earthquakeList = new LinkedList < WidgetClass > ();
                        titleList = new LinkedList < String > ();

                        for (int i = 0; i < 24; i++) {
                            eventType = xpp.next();
                        }
                    } else
                    if (xpp.getName().equalsIgnoreCase("item")) {
                        widget = new WidgetClass();
                    } else
                    if (xpp.getName().equalsIgnoreCase("title")) {
                        // Now just get the associated text
                        String temp = xpp.nextText();
                        widget.setTitle(temp);

                    } else
                    if (xpp.getName().equalsIgnoreCase("description")) {
                            // Now just get the associated text
                            String temp = xpp.nextText();
                            widget.setDescription(temp);

                            String[] descriptor = temp.trim().split(";");

                            for (int i = 0; i < descriptor.length; i++) {
                                String[] property = descriptor[i].trim().split(":");

                                switch ("" + i) {
                                    case "1":
                                        widget.setLocation(property[1].trim());
                                        Log.d("locations", "" + widget.getLocation());
                                        break;
                                    case "2":
                                        //get valuel
                                        //split it by comma
                                        String lat_long[] = property[1].trim().split( "," );
                                        //add value1 to lat
                                        widget.setLatitude( lat_long[0] );
                                        //add value2 to long
                                        widget.setLongitude( lat_long[1] );
                                        break;
                                    case "3":
                                        String depth = property[1].trim().substring(0, property[1].trim().length() - 3);
                                        widget.setDepth(depth);
                                        Log.d("depth", "" + widget.getDepth());
                                    case "4":
                                        widget.setMagnitude(property[1].trim());
                                        Log.d("magni", "" + widget.getMagnitude());
                                }
                            }

                        } else
                    if (xpp.getName().equalsIgnoreCase("pubDate")) {
                                // Now just get the associated text
                                String temp = xpp.nextText();
                                widget.setPubDate(temp);
                            } else
                    if (xpp.getName().equalsIgnoreCase("link")) {
                        // Now just get the associated text
                        String temp = xpp.nextText();
                        widget.setLink(temp);
                    } else
                    if (xpp.getName().equalsIgnoreCase("category")) {
                        String temp = xpp.nextText();
                        widget.setCategory(temp);
                    } else
                    if (xpp.getName().equalsIgnoreCase("geo:lat")) {
                        // Now just get the associated text
                        String temp = xpp.nextText();
                        widget.setLatitude(temp);
                    } else
                    if (xpp.getName().equalsIgnoreCase("geo:long")) {
                        // Now just get the associated text
                        String temp = xpp.nextText();
                        widget.setLongitude(temp);
                    }
                } else
                if (eventType == XmlPullParser.END_TAG) {
                    if (xpp.getName().equalsIgnoreCase("item")) {
                        earthquakeList.add(widget);
                        titleList.add(widget.getTitle());
                    } else
                    if (xpp.getName().equalsIgnoreCase("channel")) {
                        int size;
                        size = earthquakeList.size();
                    }
                }

                // Get the next event
                eventType = xpp.next();
            }

        } catch (XmlPullParserException e) {
            System.out.println("Parsing Error " + e.toString());
        } catch (IOException e) {
            System.out.println("Parsing Error " + e.toString());
        }
        return earthquakeList;
    }

    // THE END
}