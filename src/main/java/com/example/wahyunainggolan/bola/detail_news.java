package com.example.wahyunainggolan.bola;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wahyunainggolan.bola.adapter.Detail_news_adapter;
import com.example.wahyunainggolan.bola.adapter.NewsAdapter;
import com.example.wahyunainggolan.bola.fragment.News;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.squareup.picasso.Picasso;
import com.ypyproductions.utils.ApplicationUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class detail_news extends AppCompatActivity {
    ListView listview;
    Detail_news_adapter adapter;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String, String>> arraylist;
    //    String news_url;
    String news_detail_img, news_detail_heading, news_deatil_desc;
    Toolbar mToolbar;
    public static final String ADMOB_ID_BANNER = "ca-app-pub-xxxxxxxxxxxxxxxx/xxxxxxxxxxx";
    public static final String ADMOB_ID_INTERTESTIAL = "ca-app-pub-xxxxxxxxxxxxxxxx/xxxxxxxxxxx";
    InterstitialAd mInterstitial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        String judul = getIntent().getExtras().getString("population");
        String news_url= getIntent().getExtras().getString("href");
        new JsoupListView().execute();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setUpLayoutAdmob1();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setUpLayoutAdmob1() {

        if (ApplicationUtils.isOnline(this)) {
            boolean b = true;
            if (b) {
                mInterstitial = new InterstitialAd(this);
                mInterstitial.setAdUnitId(ADMOB_ID_INTERTESTIAL);
                mInterstitial.loadAd(new AdRequest.Builder().build());
                mInterstitial.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        // TODO Auto-generated method stub
                        super.onAdLoaded();
                        if (mInterstitial.isLoaded()) {
                            mInterstitial.show();
                        }
                    }
                });

            }
        }
    }
    @Override
    public void onBackPressed() {

        if(getFragmentManager().getBackStackEntryCount() == 3) {
            moveTaskToBack(false);
        }
        else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private class JsoupListView extends AsyncTask<Void, Void, Void> {
        News news;
        Bitmap bitmap;
        String news_url= getIntent().getExtras().getString("href");

        public JsoupListView() {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(detail_news.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Selengkapnya");
            // Set progressdialog message
            mProgressDialog.setMessage("Menunggu...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            arraylist = new ArrayList<HashMap<String, String>>();

            try {
                Document doc = Jsoup.connect(news_url).get();
                HashMap<String, String> map = new HashMap<String, String>();

                Element link = doc.select("div[class=article-content-body__item-content]").first();
                Element judul = doc.select("h1[class=read-page--header--title entry-title]").first();

                map.put("href",link.text());
                map.put("population",judul.text());
                arraylist.add(map);


            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return null;
        }
        private View babi;
        private JsoupListView(View tena){
            this.babi=tena;
        }

        @Override
        protected void onPostExecute(Void result) {
            listview = (ListView) findViewById(R.id.listview_detail_news);
            System.out.println("dian"+listview);
            adapter = new Detail_news_adapter(detail_news.this, arraylist);
            listview.setAdapter(adapter);
            mProgressDialog.dismiss();
        }
    }


}