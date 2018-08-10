package com.example.wahyunainggolan.bola.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wahyunainggolan.bola.ImageLoader;
import com.example.wahyunainggolan.bola.R;
import com.example.wahyunainggolan.bola.detail_news;
import com.example.wahyunainggolan.bola.fragment.News;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Wahyu Nainggolan on 11/06/2018.
 */

public class NewsAdapter extends BaseAdapter  {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> resultp = new HashMap<String, String>();
    ImageLoader imageLoader;
    public NewsAdapter(Context context,
                                    ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
        imageLoader = new ImageLoader(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        // Declare Variables
        TextView rank;
        TextView country;
        TextView population;
        ImageView flag;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.news_item, parent, false);
        // Get the position
        resultp = data.get(position);
        System.out.println("magdalena"+data.get(position));
        // Locate the TextViews in listview_item.xml
        country = (TextView) itemView.findViewById(R.id.country);
        population = (TextView) itemView.findViewById(R.id.population);
        flag = (ImageView) itemView.findViewById(R.id.flag);

        country.setText(resultp.get(News.COUNTRY));
        population.setText(resultp.get(News.POPULATION));
        imageLoader.DisplayImage(resultp.get(News.FLAG), flag);

        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get the position
                resultp = data.get(position);
                Intent intent = new Intent(context, detail_news.class);
                // Pass all data rank
                intent.putExtra("rank", resultp.get(News.RANK));
                // Pass all data country
                intent.putExtra("country", resultp.get(News.COUNTRY));
                // Pass all data population
                intent.putExtra("population",resultp.get(News.POPULATION));
                // Pass all data flag
                intent.putExtra("flag", resultp.get(News.FLAG));
                // Start SingleItemView Class
                intent.putExtra("href", resultp.get(News.HREF));

                context.startActivity(intent);

            }
        });
        return itemView;
    }


}
