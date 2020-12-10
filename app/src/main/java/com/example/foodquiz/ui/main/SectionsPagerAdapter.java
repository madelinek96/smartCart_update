package com.example.foodquiz.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.foodquiz.Frag1;
import com.example.foodquiz.Frag2;
import com.example.foodquiz.Frag3;
import com.example.foodquiz.Frag4;
import com.example.foodquiz.Frag5;
import com.example.foodquiz.Frag6;
import com.example.foodquiz.Frag7;
import com.example.foodquiz.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.sunday_tab, R.string.monday_tab,R.string.tuesday_tab,R.string.wednesday_tab,R.string.thursday_tab,R.string.friday_tab,R.string.saturday_tab};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch(position){
            case 0:
                fragment = new Frag1();
                break;
            case 1:
                fragment = new Frag2();
                break;
            case 2:
                fragment = new Frag3();
                break;
            case 3:
                fragment = new Frag4();
                break;
            case 4:
                fragment = new Frag5();
                break;
            case 5:
                fragment = new Frag6();
                break;
            case 6:
                fragment = new Frag7();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 7 total pages.
        return 7;
    }
}