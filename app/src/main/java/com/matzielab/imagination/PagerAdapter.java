package com.matzielab.imagination;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Matzie on 2016-10-25.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> slides = new ArrayList<>();

    public PagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.prepareSlides();
    }

    private void prepareSlides() {
        slides.add(new StartpageFragment());

        for (int i=0; i<3; i++) {
            Fragment slide = new BasicArtFragment();
            slides.add(slide);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return slides.get(position);
    }

    @Override
    public int getCount() {
        return slides.size();
    }
}
