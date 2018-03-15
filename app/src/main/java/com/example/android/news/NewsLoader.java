package com.example.android.news;

import android.content.AsyncTaskLoader;
import android.content.Context;

import org.json.JSONException;

import java.util.List;

/**
 * Created by Kamil on 2018-01-17.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private String mUrl;
    private static final String LOG_TAG = NewsLoader.class.getName();

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }


    @Override
    public List<News> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        List<News> news = null;
        try {
            news = QueryUtils.fetchNewsData(mUrl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return news;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
