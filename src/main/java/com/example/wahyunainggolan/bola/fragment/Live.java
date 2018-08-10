package com.example.wahyunainggolan.bola.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.wahyunainggolan.bola.App.Constants;
import com.example.wahyunainggolan.bola.R;
import com.example.wahyunainggolan.bola.adapter.LiveAdapter;
import com.example.wahyunainggolan.bola.adapter.NewsAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Live extends Fragment {
    ListView listview;
    LiveAdapter adapter;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String, String>> arraylist;
    public static String DATE = "date";
    public static String HOME = "home";
    Constants constants;
    public static String AWAY = "away";
    public static String SCORE = "score";
    public static String HREF = "href";

    View rootView;

    // URL Address
    String url = "https://us.soccerway.com/international/world/world-cup/2018-russia/group-stage/r32579/matches";
    String detail="https://us.soccerway.com";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.lives, container, false);
        new JsoupListView().execute();
        return rootView;
    }
    private class JsoupListView extends AsyncTask<Void, Void, Void> {
        News news;

        public JsoupListView() {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(getContext());
            // Set progressdialog title
            mProgressDialog.setTitle("Livescore ni Bolahita");
            // Set progressdialog message
            mProgressDialog.setMessage("Menunggu...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create an array
            arraylist = new ArrayList<HashMap<String, String>>();

            try {
                Document doc = Jsoup.connect(url).get();
                for (Element table : doc.select("table[class=matches]")) {
                    for (Element row : table.select("tr:gt(0)")) {
                        constants = new Constants();

                        HashMap<String, String> map = new HashMap<String, String>();
                        Elements tds = row.select("td");
                        Elements links = tds.select("a[href]");
//                        for (Element link : links) {
                        String relHref=links.get(1).attr("href");

                        String refDetail=detail + relHref;
                        System.out.println("tena : "+ refDetail);
                        map.put("date", tds.get(1).text());
                        map.put("home", tds.get(2).text());
                        map.put("away", tds.get(4).text());
                        map.put("score", tds.get(3).text());
                        map.put("href",refDetail.toString());

                        arraylist.add(map);
                    }
                }
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
            listview = (ListView)getView().findViewById(R.id.listview_lives);
            adapter = new LiveAdapter(getContext(), arraylist);
            listview.setAdapter(adapter);
            mProgressDialog.dismiss();
        }
    }

}
