package com.chandrakant.abc.crm_app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by ABC on 02/08/2017.
 */

public class PiePageAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments = new ArrayList<>();

    ArrayList<String> tabTitles = new ArrayList<>();

    public PiePageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragments(Fragment fragments, String titles)
    {
        this.fragments.add(fragments);
        this.tabTitles.add(titles);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                return new PieFragmentOne();
            case 1:
                return new PieFragmentTwo();
            default:
                break;
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }
}
