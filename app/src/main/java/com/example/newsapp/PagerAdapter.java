package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    int tabCount;

    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new Home();
            case 1: return new Technology();
            case 2: return new Sports();
            case 3: return new National();
            case 4: return new Health();
            case 5: return new Entertainment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
