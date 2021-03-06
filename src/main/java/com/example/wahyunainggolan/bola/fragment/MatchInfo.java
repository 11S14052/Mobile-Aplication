package com.example.wahyunainggolan.bola.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wahyunainggolan.bola.App.Constants;
import com.example.wahyunainggolan.bola.R;
import com.example.wahyunainggolan.bola.adapter.LiveAdapter;
import com.example.wahyunainggolan.bola.adapter.MatchInfoAdapter;
import com.example.wahyunainggolan.bola.detail_news;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MatchInfo extends Fragment {
    View rootView;
    ListView listview;
    MatchInfoAdapter adapter;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String, String>> arraylist;
    public static String GOAL = "goal";
    public static String HOMEGOAL = "home goal";
    public static String AWAYGOAL = "away goal";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView team_a;
        rootView = inflater.inflate(R.layout.fragment_match_info, container, false);
        //team_a = (TextView) rootView.findViewById(R.id.);

        new JsoupListView().execute();

        return rootView;
    }

    private class JsoupListView extends AsyncTask<Void, Void, Void> {
        String match_url= getActivity().getIntent().getExtras().getString("href");

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
        Document doc = Jsoup.connect(match_url).get();
        for (Element table : doc.select("table[class=matches events]")) {
            for (Element row : table.select("tr")) {

                HashMap<String, String> map = new HashMap<String, String>();
                Elements tds = row.select("td");


                System.out.println("score : " + tds.get(1).text());
                System.out.println("home goal : " + tds.get(0).text());
                System.out.println("away goal : " + tds.get(2).text());

                map.put("goal",  tds.get(1).text());
                map.put("home goal",  tds.get(0).text());
                map.put("away goal",  tds.get(2).text());


                //map.put("home", tds.get(2).text());
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
            System.out.println("galau"+arraylist);
            listview = (ListView)getView().findViewById(R.id.listview_matchinfo);
            adapter = new MatchInfoAdapter(getContext(), arraylist);
            listview.setAdapter(adapter);
            mProgressDialog.dismiss();
        }
    }


}
