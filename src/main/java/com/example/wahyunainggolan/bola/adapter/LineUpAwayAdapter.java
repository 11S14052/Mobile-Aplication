package com.example.wahyunainggolan.bola.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wahyunainggolan.bola.R;
import com.example.wahyunainggolan.bola.fragment.LineUp;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Wahyu Nainggolan on 27/06/2018.
 */

public class LineUpAwayAdapter extends BaseAdapter {
    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public LineUpAwayAdapter(Context context,
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


    public View getView(final int position, View convertView, ViewGroup parent) {
        LiveAdapter.ViewHolderItem holder = new LiveAdapter.ViewHolderItem();

        // Declare Variables
        TextView playerleft;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.lineup_away_info_item, parent, false);
        resultp = data.get(position);
        playerleft = (TextView) itemView.findViewById(R.id.position);
        playerleft.setText(resultp.get(LineUp.PLAYERLEFT));
        return itemView;

    }
}

