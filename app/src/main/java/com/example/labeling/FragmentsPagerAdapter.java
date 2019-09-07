package com.example.labeling;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class FragmentsPagerAdapter extends FragmentStatePagerAdapter {

    List<String> mKeyList = new ArrayList<>();
    private List<Fragment> fragments;
    List<Fragment> fList = new ArrayList<Fragment>();
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;

    public FragmentsPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentManager = fm;
        fragments = new ArrayList<>();
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments.clear();
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "SCOUT " + (getCount() - position);
    }

    public void add(int position, String key) {
        mKeyList.add(position, key);
        notifyDataSetChanged();
    }

    public List<Fragment> getFragments() {
        fList.clear();
        for (int i = 0; i < LabelData.imageUrlFromDB.size(); i++) {
            fList.add(viewpagerFragment.newInstance(LabelData.imageUrlFromDB.get(i), "image"));
        }
        for (int i = 0; i < LabelData.textFromDB.size(); i++) {
            fList.add(viewpagerFragment.newInstance(LabelData.textFromDB.get(i), "text"));
        }
        for (int i = 0; i < LabelData.videoUrlFromDB.size(); i++) {
            fList.add(viewpagerFragment.newInstance(LabelData.videoUrlFromDB.get(i), "video"));
        }
        notifyDataSetChanged();
        return fList;
    }
}