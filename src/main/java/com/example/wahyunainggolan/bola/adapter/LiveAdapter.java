package com.example.wahyunainggolan.bola.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wahyunainggolan.bola.R;
import com.example.wahyunainggolan.bola.detail_livescore;
import com.example.wahyunainggolan.bola.fragment.Live;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Wahyu Nainggolan on 12/06/2018.
 */

public class LiveAdapter extends BaseAdapter {
    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public LiveAdapter(Context context,
                       ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
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

    public static class ViewHolderItem {
        ImageView img_datapath;
        public TextView home_team_name, teams_score, away_team_name, counteryName;
        public String counteryFlag, home_team_score, away_team_score;
        private final int[] bgColors = new int[]{R.drawable.live_top_bar_blue, R.drawable.live_top_bar_green};
        public RelativeLayout bg_top_bar;
        public String date;
        public String date_str;
        public TextView date_txt;
        public ImageView img_datapath_away, img_datapath_home;
        public String home_logo, away_logo;
        public TextView minutes;
        public String minutes_str;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolderItem holder = new ViewHolderItem();

        // Declare Variables
        TextView date;
        TextView home;
        TextView score;
        TextView away;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.lives_item, parent, false);
            // Get the position
        resultp = data.get(position);
            // Locate the TextViews in listview_item.xml
        date = (TextView) itemView.findViewById(R.id.date);
        home = (TextView) itemView.findViewById(R.id.home);
        score = (TextView) itemView.findViewById(R.id.score);
        away = (TextView) itemView.findViewById(R.id.away);

        if (home != null || away != null) {
            int colorPosition = position % holder.bgColors.length;
            date.setBackgroundResource(holder.bgColors[colorPosition]);
            date.setText(resultp.get(Live.DATE));
            home.setText(resultp.get(Live.HOME));
            score.setText(resultp.get(Live.SCORE));
            away.setText(resultp.get(Live.AWAY));
            itemView.setTag(holder);
        }


        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get the position
                resultp = data.get(position);
                Intent intent = new Intent(context, detail_livescore.class);
                intent.putExtra("href", resultp.get(Live.HREF));
                intent.putExtra("home", resultp.get(Live.HOME));
                intent.putExtra("away", resultp.get(Live.AWAY));
                intent.putExtra("score", resultp.get(Live.SCORE));



                context.startActivity(intent);

            }
        });

        return itemView;
    }


}
