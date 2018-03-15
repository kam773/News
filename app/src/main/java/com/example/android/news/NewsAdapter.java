package com.example.android.news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Kamil on 2018-01-17.
 */

public class NewsAdapter extends ArrayAdapter<News> {


    public NewsAdapter(Context context, ArrayList<News> news) {
        super(context, 0, news);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        News currentNews = getItem(position);

        TextView title = (TextView) listItemView.findViewById(R.id.item_title);
        title.setText(currentNews.getmName());

        TextView section = (TextView) listItemView.findViewById(R.id.item_section);
        section.setText(currentNews.getmDescription());

        TextView pillar = (TextView) listItemView.findViewById(R.id.item_pillar);
        pillar.setText(currentNews.getmCategory());

        TextView date = (TextView) listItemView.findViewById(R.id.date_item);
        date.setText(currentNews.getmDate());

        return listItemView;


    }
}
