package com.example.android.news;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {


    private TextView mEmptyState;
    private NewsAdapter mAdapter;

    private static final String BASE_URL = "https://content.guardianapis.com/search?api-key=2e0fcb70-724d-4219-9b23-e62cc82e1d26";
    private static final String BUSINESS_URL = "http://content.guardianapis.com/sections?q=business&api-key=2e0fcb70-724d-4219-9b23-e62cc82e1d26";
    private static final String SPORTS_URL = "http://content.guardianapis.com/sections?q=sports&api-key=2e0fcb70-724d-4219-9b23-e62cc82e1d26";
    private static final String ENTERTAINMENT_URL = "http://content.guardianapis.com/sections?q=entertainment&api-key=2e0fcb70-724d-4219-9b23-e62cc82e1d26";
    private static final String TECHNOLOGY_URL = "http://content.guardianapis.com/sections?q=technology&api-key=2e0fcb70-724d-4219-9b23-e62cc82e1d26";
    private static final String HEALTH_URL = "http://content.guardianapis.com/sections?q=health&api-key=2e0fcb70-724d-4219-9b23-e62cc82e1d26";
    private static final String SCIENCE_URL = "http://content.guardianapis.com/sections?q=science&api-key=2e0fcb70-724d-4219-9b23-e62cc82e1d26";

    private static final int NEWS_LOADER_ID = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView newsListView = (ListView) findViewById(R.id.listView);

        mEmptyState = (TextView) findViewById(R.id.text_no_data_found);
        newsListView.setEmptyView(mEmptyState);

        mAdapter = new NewsAdapter(this, new ArrayList<News>());

        newsListView.setAdapter(mAdapter);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News currentNews = mAdapter.getItem(position);

                Uri newsUri = Uri.parse(currentNews.getmUrl());

                Intent webIntent = new Intent(Intent.ACTION_VIEW, newsUri);

                startActivity(webIntent);
            }
        });


        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            LoaderManager loaderManager = getLoaderManager();

            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {

            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            mEmptyState.setText(R.string.no_internet_connection);
        }
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {





        return new NewsLoader(this, BASE_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        mEmptyState.setText(R.string.no_news);

        mAdapter.clear();

        if (news != null && !news.isEmpty()) {
            mAdapter.addAll(news);

        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        mAdapter.clear();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
