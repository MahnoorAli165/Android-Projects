package com.example.chhotay;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class OrderAdapter extends FragmentPagerAdapter
{
    int tabcount;
    public OrderAdapter(@NonNull FragmentManager fm, int behavior)
    {
        super(fm, behavior);
        tabcount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new CurrentOrderFragment();
            case 1: return new HistoryFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return tabcount;
    }

}
