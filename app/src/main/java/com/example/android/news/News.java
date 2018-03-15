package com.example.android.news;

/**
 * Created by Kamil on 2018-01-17.
 */

public class News {

    private String mName;
    private String mUrl;
    private String mDescription;
    private String mCategory;
    private String mDate;


    public News(String title, String url, String section, String pillar, String date) {
        mName = title;
        mUrl = url;
        mDescription = section;
        mCategory = pillar;
        mDate = date;

    }

    public String getmName() {
        return mName;
    }

    public String getmUrl() {
        return mUrl;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmCategory() {
        return mCategory;
    }

    public String getmDate() {
        return mDate;
    }
}
