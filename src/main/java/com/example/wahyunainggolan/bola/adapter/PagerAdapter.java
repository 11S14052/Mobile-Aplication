package com.example.wahyunainggolan.bola.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.wahyunainggolan.bola.fragment.Live;
import com.example.wahyunainggolan.bola.fragment.News;
import com.example.wahyunainggolan.bola.fragment.Prediksi;

/**
 * Created by Wahyu Nainggolan on 10/06/2018.
 */


public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Live tab1 = new Live();
                return tab1;
            case 1:
                Prediksi tab2 = new Prediksi();
                return tab2;
            case 2:
                News tab3 = new News();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}