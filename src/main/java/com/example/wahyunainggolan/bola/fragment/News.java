package com.example.wahyunainggolan.bola.fragment;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.wahyunainggolan.bola.R;
import com.example.wahyunainggolan.bola.adapter.NewsAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class News extends Fragment {
    ListView listview;
    NewsAdapter adapter;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String, String>> arraylist;
    public static String RANK = "rank";
    public static String COUNTRY = "country";
    public static String POPULATION = "population";
    public static String FLAG = "flag";
    public static String HREF = "href";

    View rootView;

    // URL Address
    String url = "https://www.bola.com/pesta-bola-rusia";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.news, container, false);
        new JsoupListView().execute();
        return rootView;
    }
    private class JsoupListView extends AsyncTask<Void, Void, Void> {
        News news;
        Bitmap bitmap;
        public JsoupListView() {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(getContext());
            // Set progressdialog title
            mProgressDialog.setTitle("Berita ni Bolahita");
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
                Document doc = Jsoup.connect(url).get();
                Elements mElementDataSize = doc.select("article[class=articles--iridescent-list--item articles--iridescent-list--text-item]");
                int mElementSize = mElementDataSize.size();

                for (int i = 0; i < mElementSize; i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    Elements mElementBlogUploadDate = doc.select("span[class=articles--iridescent-list--text-item__datetime]").eq(i);
                    Elements mElementBlogTitle = doc.select("h4[class=articles--iridescent-list--text-item__title]").eq(i);

                    Elements figure = doc.select("figure[class=articles--iridescent-list--text-item__figure-thumbnail]").eq(i);
  //                  Elements picture = figure.select("a[class=ui--a articles--iridescent-list--text-item__figure-thumbnail-link]").after("figure");
//                    Elements jing = picture.select("picture[class=articles--iridescent-list--text-item__figure-image]").before("img");
//                    Element tenas = jing.select("img[class=articles--iridescent-list--text-item__figure-image-lazyload lazyloaded]").last();
                    String imgSrcStr = figure.attr("title"); // == "/"


                    Elements link = doc.select("h4[class=articles--iridescent-list--text-item__title]").eq(i);
                    Element tena = link.select("a[class=ui--a articles--iridescent-list--text-item__title-link]").first();
                    String relHref = tena.attr("href"); // == "/"

                    map.put("country",mElementBlogUploadDate.text());
                    map.put("population",mElementBlogTitle.text());

                    map.put("flag", imgSrcStr);
                    map.put("href",relHref.toString());

                    arraylist.add(map);
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
            listview = (ListView)getView().findViewById(R.id.listview_news);
            System.out.println("notanota"+listview);
            adapter = new NewsAdapter(getContext(), arraylist);
            listview.setAdapter(adapter);
            mProgressDialog.dismiss();
        }
    }

}
