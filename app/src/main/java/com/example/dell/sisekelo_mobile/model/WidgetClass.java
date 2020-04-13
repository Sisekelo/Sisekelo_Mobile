package com.example.dell.sisekelo_mobile.model;

/*SISEKELO DLAMINI S1719039*/

public class WidgetClass {

    private String title,description,link,pubDate,category,latitude,longitude,location,depth,magnitude;

    public WidgetClass(String title, String description, String link, String pubDate, String category, String latitude, String longitude,String location,String depth,String magnitude) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.pubDate = pubDate;
        this.category = category;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
        this.depth = depth;
        this.magnitude = magnitude;
    }

    public WidgetClass() {
        title = "";
        description = "";
        link = "";
        pubDate = "";
        category = "";
        latitude = "";
        longitude = "";
        location = "";
        depth = "";
        magnitude = "";
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
