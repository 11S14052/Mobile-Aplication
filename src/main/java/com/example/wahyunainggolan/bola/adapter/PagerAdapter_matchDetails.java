package com.example.wahyunainggolan.bola.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.wahyunainggolan.bola.fragment.Commentary;
import com.example.wahyunainggolan.bola.fragment.LineUp;
import com.example.wahyunainggolan.bola.fragment.MatchInfo;
import com.example.wahyunainggolan.bola.fragment.statistics;

/**
 * Created by Wahyu Nainggolan on 19/06/2018.
 */

public class PagerAdapter_matchDetails extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter_matchDetails(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MatchInfo tab1 = new MatchInfo();
                return tab1;
            case 1:
                LineUp tab2 = new LineUp();
                return tab2;
            case 2:
                Commentary tab3 = new Commentary();
                return tab3;
            case 3:
                statistics tab4 = new statistics();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}