package com.example.wahyunainggolan.bola.adapter;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wahyunainggolan.bola.ImageLoader;
import com.example.wahyunainggolan.bola.R;
import com.example.wahyunainggolan.bola.fragment.News;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Wahyu Nainggolan on 14/06/2018.
 */

public class Detail_news_adapter extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> resultp = new HashMap<String, String>();
    ImageLoader imageLoader;


    Toolbar mToolbar;
    public static final String ADMOB_ID_BANNER = "ca-app-pub-xxxxxxxxxxxxxxxx/xxxxxxxxxxx";
    public static final String ADMOB_ID_INTERTESTIAL = "ca-app-pub-xxxxxxxxxxxxxxxx/xxxxxxxxxxx";
    InterstitialAd mInterstitial;
    ImageView top_img;
    TextView heading,desc;

    public Detail_news_adapter(Context context,
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


        TextView href;
        TextView country;
        TextView judul;
        ImageView flag;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.content_news_detail, parent, false);
        // Get the position
        resultp = data.get(position);
        System.out.println("silaban :" + resultp);
        href = (TextView) itemView.findViewById(R.id.desc_news);
        judul =(TextView) itemView.findViewById(R.id.heading);
        href.setText(resultp.get(News.HREF));
        judul.setText(resultp.get(News.POPULATION));
        return itemView;


    }


}
