package com.example.wahyunainggolan.bola.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wahyunainggolan.bola.R;
import com.example.wahyunainggolan.bola.detail_news;
import com.example.wahyunainggolan.bola.fragment.News;
import com.example.wahyunainggolan.bola.model.Predict;

import java.util.ArrayList;

/**
 * Created by Wahyu Nainggolan on 20/06/2018.
 */

public class PredictAdapter extends BaseAdapter {
    private Context context; //context
//    Activity activity;
//    List<Predict>lstPredict;
    private ArrayList<Predict> lstPredict;
//    List<Predict>lstPredict;

    public PredictAdapter(Context context, ArrayList<Predict> lstPredict) {
        this.context = context;
        this.lstPredict = lstPredict;
    }
    LayoutInflater inflater;



    @Override
    public int getCount() {
        return lstPredict.size();
    }

    @Override
    public Object getItem(int i) {
        return lstPredict.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.predict_item,null);
        final Predict currentItem = (Predict) getItem(i);
        TextView txthometeam=(TextView)itemView.findViewById(R.id.home_team_name);
        TextView txtawayteam=(TextView)itemView.findViewById(R.id.away_team_name);
        txthometeam.setText(currentItem.getHometeam());
        txtawayteam.setText(currentItem.getAwayteam());


        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(currentItem.getHref()));
                context.startActivity(intent);
            }
        });

        return itemView;
    }
}
