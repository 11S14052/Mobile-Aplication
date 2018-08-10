package com.example.wahyunainggolan.bola;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wahyunainggolan.bola.adapter.Detail_Live_adapter;
import com.example.wahyunainggolan.bola.adapter.PagerAdapter_matchDetails;
import com.example.wahyunainggolan.bola.fragment.Live;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.ypyproductions.utils.ApplicationUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Wahyu Nainggolan on 19/06/2018.
 */

public class detail_livescore  extends AppCompatActivity {

    ListView listview;
    Detail_Live_adapter adapter;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String, String>> arraylist;
    String news_detail_img, news_detail_heading, news_deatil_desc;
    Toolbar mToolbar;
    public static final String ADMOB_ID_BANNER = "ca-app-pub-xxxxxxxxxxxxxxxx/xxxxxxxxxxx";
    public static final String ADMOB_ID_INTERTESTIAL = "ca-app-pub-xxxxxxxxxxxxxxxx/xxxxxxxxxxx";
    InterstitialAd mInterstitial;
    ImageView top_img;
    TextView heading,desc;
    ViewPager viewPager;
    RelativeLayout hidden_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_live);
        TextView home;
        TextView away;
        TextView score;

        away = (TextView) findViewById(R.id.textAway);
        home = (TextView) findViewById(R.id.textHome);
        score = (TextView) findViewById(R.id.textScoreHome);

        away.setText(getIntent().getExtras().getString("away"));
        home.setText(getIntent().getExtras().getString("home"));
        score.setText(getIntent().getExtras().getString("score"));
        String news_url = getIntent().getExtras().getString("href");
//        new JsoupListView().execute();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setUpLayoutAdmob1();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
//        hidden_layout = (RelativeLayout) findViewById(R.id.hidden_layout);

        tabLayout.addTab(tabLayout.newTab().setText("Match Info"));
        tabLayout.addTab(tabLayout.newTab().setText("Line Up Team Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Line Up Team Away"));
        tabLayout.addTab(tabLayout.newTab().setText("Statistic"));
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter_matchDetails adapter = new PagerAdapter_matchDetails(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    mToolbar.setTitle("Match Info");
                } if (tab.getPosition() == 1) {
                    mToolbar.setTitle("Line Up Team Home");

                } if (tab.getPosition() == 2) {
                    mToolbar.setTitle("Line Up Team Away");

                } if (tab.getPosition() == 3) {
                    mToolbar.setTitle("Statistic");

                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        // Toast.makeText(Live_matches_Details.this, match_id, Toast.LENGTH_SHORT).show();

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

//    private class JsoupListView extends AsyncTask<Void, Void, Void> {
//        Live live;
//        Bitmap bitmap;
//        String news_url= getIntent().getExtras().getString("href");
//
//        public JsoupListView() {
//
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            // Create a progressdialog
//            mProgressDialog = new ProgressDialog(detail_livescore.this);
//            // Set progressdialog title
//            mProgressDialog.setTitle("Selengkapnya");
//            // Set progressdialog message
//            mProgressDialog.setMessage("Menunggu...");
//            mProgressDialog.setIndeterminate(false);
//            // Show progressdialog
//            mProgressDialog.show();
//        }
//
//        @Override
//        protected Void doInBackground(Void... params) {
//            arraylist = new ArrayList<HashMap<String, String>>();
//
//            try {
//                Document doc = Jsoup.connect(news_url).get();
//                HashMap<String, String> map = new HashMap<String, String>();
//
//                Element left = doc.select("div[class=container left]").first();
//                Elements home_team=left.select("h3[class=thick]");
//                Element right = doc.select("div[class=container right]").first();
//                Elements away_team=right.select("h3[class=thick]");
//
//                Elements score=doc.select("h3[class=thick scoretime ]");
//
//                map.put("hometeam",home_team.text());
//                map.put("awayteam",away_team.text());
//                map.put("score",score.text());
//
//                arraylist.add(map);
//
//
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//        private View babi;
//
//        private JsoupListView(View tena){
//            this.babi=tena;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            listview = (ListView) findViewById(R.id.listview_detail_livescore);
//            System.out.println("dian"+arraylist);
//            adapter = new Detail_Live_adapter(detail_livescore.this, arraylist);
//            System.out.println("dian"+listview);
//
//            listview.setAdapter(adapter);
//            mProgressDialog.dismiss();
//        }
//    }

}